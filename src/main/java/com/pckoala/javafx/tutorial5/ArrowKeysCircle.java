package com.pckoala.javafx.tutorial5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ArrowKeysCircle extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create a border pane
    Pane pane = new Pane();

    // Create a circle and add it to the center of the pane
    double radius = 30;
    Circle circle = new Circle(200, 200, radius);
    circle.setFill(Color.VIOLET);
    pane.getChildren().add(circle);

    // Create event handlers
    circle.setOnKeyPressed(
        event -> {
          double x = circle.getCenterX();
          double y = circle.getCenterY();
          switch (event.getCode()) {
            case UP -> circle.setCenterY(y > radius ? y - 10 : y);
            case DOWN -> circle.setCenterY(y < pane.getHeight() - radius ? y + 10 : y);
            case LEFT -> circle.setCenterX(x > radius ? x - 10 : x);
            case RIGHT -> circle.setCenterX(x < pane.getWidth() - radius ? x + 10 : x);
          }
        });

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 400);
    primaryStage.setTitle("ArrowKeysCircle");
    primaryStage.setScene(scene);
    primaryStage.show();

    circle.requestFocus();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
