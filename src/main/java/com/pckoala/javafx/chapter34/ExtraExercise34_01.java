package com.pckoala.javafx.chapter34;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * Access and update a staff table.
 *
 * <ol>
 *   <li>View button displays a record with a specified ID.
 *   <li>Insert button inserts a new record.
 *   <li>Update button updates the record for the specified ID.
 * </ol>
 */
public class ExtraExercise34_01 extends Application {
  // Initialize TextFields
  private final TextField tfId = new TextField();
  private final TextField tfLastName = new TextField();
  private final TextField tfFirstName = new TextField();
  private final TextField tfMI = new TextField();
  private final TextField tfAddress = new TextField();
  private final TextField tfCity = new TextField();
  private final TextField tfState = new TextField();
  private final TextField tfTelephone = new TextField();
  private final TextField tfEmail = new TextField();

  // Initialize Labels
  private final Label lblStatus = new Label("Start by inputting staff details in the fields");
  private final Label lblId = new Label("ID");
  private final Label lblLastName = new Label("Last Name");
  private final Label lblFirstName = new Label("First Name");
  private final Label lblMI = new Label("MI");
  private final Label lblAddress = new Label("Address");
  private final Label lblCity = new Label("City");
  private final Label lblState = new Label("State");
  private final Label lblTelephone = new Label("Telephone");
  private final Label lblEmail = new Label("Email");

  // Initialize buttons
  private final Button btnView = new Button("View");
  private final Button btnInsert = new Button("Insert");
  private final Button btnUpdate = new Button("Update");
  private final Button btnClear = new Button("Clear");
  // Initialize String variables for grabbing user input from TextFields
  String id;
  String lastName;
  String firstName;
  String mi;
  String address;
  String city;
  String state;
  String telephone;
  String email;
  // Initialize MySQL statement for executing queries
  private Statement statement;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create a root VBox pane
    VBox root = new VBox();

    // Create new GridPane
    GridPane gridPane = new GridPane();
    gridPane.add(lblStatus, 0, 0, 4, 1);
    gridPane.add(lblId, 0, 1);
    gridPane.add(tfId, 1, 1);
    gridPane.add(lblLastName, 0, 2);
    gridPane.add(tfLastName, 1, 2);
    gridPane.add(lblFirstName, 2, 2);
    gridPane.add(tfFirstName, 3, 2);
    gridPane.add(lblMI, 4, 2);
    gridPane.add(tfMI, 5, 2);
    gridPane.add(lblAddress, 0, 3);
    gridPane.add(tfAddress, 1, 3);
    gridPane.add(lblCity, 0, 4);
    gridPane.add(tfCity, 1, 4);
    gridPane.add(lblState, 2, 4);
    gridPane.add(tfState, 3, 4);
    gridPane.add(lblTelephone, 0, 5);
    gridPane.add(tfTelephone, 1, 5);
    gridPane.add(lblEmail, 0, 6);
    gridPane.add(tfEmail, 1, 6);

    // GridPane padding properties
    tfMI.setPrefColumnCount(1);
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(5);
    gridPane.setVgap(5);

    // Add HBox below the GridPane and centered horizontally to hold the buttons
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btnView, btnInsert, btnUpdate, btnClear);
    hBox.setAlignment(Pos.CENTER);

    // Add all sub-panes to the root pane
    root.getChildren().addAll(gridPane, hBox);
    root.setPadding(new Insets(10));

    connectToDatabase();

    Scene scene = new Scene(root, root.getMaxWidth(), root.getMaxHeight());
    primaryStage.setResizable(false);
    primaryStage.setTitle("ExtraExercise34_01");
    primaryStage.setScene(scene);
    primaryStage.show();

    // Event handlers
    btnView.setOnAction(e -> viewStaff());
    btnInsert.setOnAction(e -> insertStaff());
    btnUpdate.setOnAction(e -> updateStaff());
    btnClear.setOnAction(e -> clearForm());
  }

  /**
   * Attempt to connect to the MySQL database using JDBC driver.<br>
   * If an error occurs, show the user a popup alert.
   */
  private void connectToDatabase() {
    try {
      Connection connection =
          DriverManager.getConnection("jdbc:mysql://localhost:3306/staff", "root", "6h%FHQyQ");
      System.out.println("Successfully connected to database.");

      // Create a statement
      statement = connection.createStatement();

    } catch (SQLException e) {
      Alert alert =
          new Alert(
              AlertType.ERROR,
              "Could not connect to database."
                  + "\nExit the program and check your MySQL connection details.",
              ButtonType.OK);
      alert.showAndWait();
      Platform.exit();
    }
  }

  private void viewStaff() {
    id = tfId.getText();

    try {
      ResultSet resultSet =
          statement.executeQuery(
              "SELECT lastName, firstName, mi, address, city, state, telephone, email"
                  + " FROM javabook.Staff WHERE javabook.Staff.id = '"
                  + id
                  + "'");

      if (resultSet.next()) {
        tfLastName.setText(resultSet.getString(1));
        tfFirstName.setText(resultSet.getString(2));
        tfMI.setText(resultSet.getString(3));
        tfAddress.setText(resultSet.getString(4));
        tfCity.setText(resultSet.getString(5));
        tfState.setText(resultSet.getString(6));
        tfTelephone.setText(resultSet.getString(7));
        tfEmail.setText(resultSet.getString(8));
        lblStatus.setText("Staff found successfully.");
        lblStatus.setTextFill(Color.GREEN);
      } else {
        lblStatus.setText("Record not found.");
        lblStatus.setTextFill(Color.RED);
      }
    } catch (SQLException e) {
      Alert alert =
          new Alert(
              AlertType.ERROR, "An error has occurred. Check console for details.", ButtonType.OK);
      alert.showAndWait();
      throw new RuntimeException(e);
    }
  }

  private void insertStaff() {
    getUserInput();
    if (isInputInvalid()) {
      System.out.println("Failed to insert staff.");
      return;
    }
    try {
      statement.execute(
          "INSERT INTO javabook.Staff ("
              + "javabook.Staff.id, "
              + "javabook.Staff.lastName, "
              + "javabook.Staff.firstName, "
              + "javabook.Staff.mi, "
              + "javabook.Staff.address, "
              + "javabook.Staff.city, "
              + "javabook.Staff.state, "
              + "javabook.Staff.telephone, "
              + "javabook.Staff.email) "
              + "VALUES ('"
              + id
              + "', '"
              + lastName
              + "', '"
              + firstName
              + "', '"
              + mi
              + "', '"
              + address
              + "', '"
              + city
              + "', '"
              + state
              + "', '"
              + telephone
              + "', '"
              + email
              + "')");
      System.out.format("Staff %s %s %s added successfully.", firstName, mi, lastName);
      lblStatus.setText("Inserted into staff database successfully.");
      lblStatus.setTextFill(Color.GREEN);

    } catch (SQLException e) {
      Alert alert =
          new Alert(
              AlertType.ERROR,
              "Staff ID:" + "%s already exists in the database.".formatted(id),
              ButtonType.OK);
      alert.showAndWait();
      throw new RuntimeException(e);
    }
  }

  private void updateStaff() {
    getUserInput();
    if (isInputInvalid()) {
      System.out.println("Failed to update staff.");
      return;
    }

    try {
      statement.execute(
          "UPDATE javabook.Staff SET javabook.Staff.lastName = '"
              + lastName
              + "', javabook.Staff.firstName = '"
              + firstName
              + "', javabook.Staff.mi = '"
              + mi
              + "', javabook.Staff.address = '"
              + address
              + "', javabook.Staff.city = '"
              + city
              + "', javabook.Staff.telephone = '"
              + telephone
              + "', javabook.Staff.email = '"
              + email
              + "' WHERE javabook.Staff.id = '" + id + "'");
      System.out.printf("Staff No.%s %s %s %s updated successfully.", id, firstName, mi, lastName);
      lblStatus.setText("Updated staff database successfully.");
      lblStatus.setTextFill(Color.GREEN);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void getUserInput() {
    id = tfId.getText();
    lastName = tfLastName.getText();
    firstName = tfFirstName.getText();
    mi = tfMI.getText();
    address = tfAddress.getText();
    city = tfCity.getText();
    state = tfState.getText();
    telephone = tfTelephone.getText();
    email = tfEmail.getText();
  }

  private boolean isInputInvalid() {
    if (id == null || !(id.matches("^0*[1-9]\\d*$"))) {
      lblStatus.setText("Please input a valid id.");
      lblStatus.setTextFill(Color.RED);
      System.out.println("Invalid id.");
      return true;
    }

    String[] strings = {lastName, firstName, mi, address, city, state, telephone, email};
    for (String s : strings) {
      if (s.isBlank()) {
        lblStatus.setText("Please fill out all fields.");
        lblStatus.setTextFill(Color.RED);
        System.out.println("Empty TextFields detected. Please fill out all fields.");
        return true;
      }
    }
    return false;
    }

    private void clearForm() {
      TextField[] textFields = {tfId, tfLastName, tfFirstName, tfMI, tfAddress, tfCity, tfState, tfTelephone, tfEmail};
      for (TextField tf : textFields) {
        tf.setText("");
      }
      lblStatus.setText("Cleared form successfully.");
      lblStatus.setTextFill(Color.GREEN);
    }
}
