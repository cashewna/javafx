package com.pckoala.javafx.tutorial4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleDemo extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create a pane and the green and red circles
    Pane pane = new Pane();
    Circle circleRed = new Circle(100, 90, 5);
    Circle circleGreen = new Circle(240, 239, 5);
    circleRed.setFill(Color.RED);
    circleGreen.setFill(Color.GREEN);

    // Add the circles to the pane
    pane.getChildren().addAll(circleRed, circleGreen);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 400);
    primaryStage.setTitle("Circle Demo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
