package com.pckoala.javafx.chapter34;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConnectionTest extends Application {
  Connection connection;
  public static void main(String[] args) throws SQLException {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    ListView<String> listView = new ListView<>();
    ObservableList<String> observableList = FXCollections.observableArrayList();
    listView.setItems(observableList);

    connectToDatabase();

    String query = "SELECT * FROM staff.personnel";
    PreparedStatement statement = connection.prepareStatement(query);
    ResultSet results = statement.executeQuery();

    while (results.next()) {
      String resultString =
          results.getString("first_name")
              + " "
              + results.getString("last_name")
              + " "
              + results.getString("address");
      observableList.add(resultString);
    }

    BorderPane pane = new BorderPane();
    pane.setCenter(listView);

    Scene scene = new Scene(pane, 400, 400);
    primaryStage.setTitle("ConnectionTest");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void connectToDatabase() {
    String url = "jdbc:mysql://localhost:3306/staff";
    String username = "root";
    String password = "6h%FHQyQ";

    try {
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      Alert alert = new Alert(AlertType.ERROR, "Something went wrong", ButtonType.OK);
      alert.showAndWait();
    }

  }
}
