package com.pckoala.javafx.chapter34;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Same code as FindGrade.java but with the use of PreparedStatement instead of Statement. Benefits
 * of PreparedStatements is that it's more efficient for repeated executions as SQL statements are
 * precompiled.
 */
public class FindGradeUsingPreparedStatement extends Application {

  private final TextField tfSSN = new TextField();
  private final TextField tfCourseId = new TextField();
  private final Label lblStatus = new Label();
  // Statements for executing SQL queries
  private PreparedStatement preparedStatement;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    initializeDB();

    Button btShowGrade = new Button("Show Grade");
    HBox hBox = new HBox(5);
    hBox.getChildren()
        .addAll(new Label("SSN"), tfSSN, new Label("Course ID"), tfCourseId, btShowGrade);

    VBox vBox = new VBox(10);
    vBox.getChildren().addAll(hBox, lblStatus);

    tfSSN.setPrefColumnCount(6);
    tfCourseId.setPrefColumnCount(6);
    btShowGrade.setOnAction(e -> showGrade());

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 420, 80);
    primaryStage.setTitle("FindGrade");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void initializeDB() {
    try {
      // Establish a connection
      Connection connection =
          DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
      System.out.println("Database connected");

      // Create a statement
      String queryString =
          "select firstName, mi, lastName, title, grade "
              + "from Student, Enrollment, Course "
              + "where Student.ssn = ? "
              + " and Enrollment.courseId = ? "
              + "and Enrollment.courseId = Course.courseId and Enrollment.ssn = Student.ssn";
      preparedStatement = connection.prepareStatement(queryString);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void showGrade() {
    String ssn = tfSSN.getText();
    String courseId = tfCourseId.getText();

    try {
      preparedStatement.setString(1, ssn);
      preparedStatement.setString(2, courseId);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        String lastName = resultSet.getString(1);
        String mi = resultSet.getString(2);
        String firstName = resultSet.getString(3);
        String title = resultSet.getString(4);
        String grade = resultSet.getString(5);

        // Display results in a label
        lblStatus.setText(
            firstName + " " + mi + " " + lastName + "'s grade on course " + title + " is " + grade);
      } else {
        lblStatus.setText("Not found");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
