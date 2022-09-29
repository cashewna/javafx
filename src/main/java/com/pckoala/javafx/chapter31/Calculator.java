package com.pckoala.javafx.chapter31;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calculator extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/com.pckoala.javafx.chapter31/FXMLDocument.fxml")));
    Scene scene = new Scene(root);
    primaryStage.setTitle("CalculatorFXML Demo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
