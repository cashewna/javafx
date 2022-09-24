package com.pckoala.javafx.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_07 extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create root border pane
    BorderPane rootPane = new BorderPane();

    // Create a blue circle of radius 50
    // and add it to the center of the rootPane
    Circle circle = new Circle(50);
    circle.setFill(Color.BLUE);
    rootPane.setCenter(circle);

    // Event handler for the circle onMousePress
    circle.setOnMousePressed(event -> circle.setFill(Color.RED));

    // Event handler  for the circle onMouseRelease
    circle.setOnMouseReleased(event -> circle.setFill(Color.BLUE));

    // Create a scene and place it on stage
    Scene scene = new Scene(rootPane, 400, 400);
    primaryStage.setTitle("Exercise 15.7");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
