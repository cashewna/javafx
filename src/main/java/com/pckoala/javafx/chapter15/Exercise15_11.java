package com.pckoala.javafx.chapter15;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/** Move a circle up, down, left, or right using the arrow keys */
public class Exercise15_11 extends Application {
  double radius = 50f;
  Circle circle = new Circle(radius);

  @Override
  public void start(Stage primaryStage) {
    // Create a root stack pane
    StackPane rootPane = new StackPane();
    Scene scene = new Scene(rootPane, 400, 400);

    // Create a new circle and set its properties
    // Add the circle to the rootPane and set its position to the center of the window
    circle.setFill(Color.BLUE);
    rootPane.getChildren().add(circle);

    // Event handler for circle
    scene.setOnKeyPressed(
        event -> {
          double x = circle.getCenterX();
          double y = circle.getCenterY();
          double moveDistance = 50f;

          switch (event.getCode()) {
            case UP -> {
              if (y > -(rootPane.getHeight() / 2) + radius) {
                startAnimation(x, y - moveDistance);
                circle.setCenterY(y - moveDistance);
              }
            }
            case DOWN -> {
              if (y < (rootPane.getHeight() / 2 - radius)) {
                startAnimation(x, y + moveDistance);
                circle.setCenterY(y + moveDistance);
              }
            }
            case LEFT -> {
              if (x > -(rootPane.getWidth() / 2) + radius) {
                startAnimation(x - moveDistance, y);
                circle.setCenterX(x - moveDistance);
              }
            }
            case RIGHT -> {
              if (x < (rootPane.getWidth() / 2) - radius) {
                startAnimation(x + moveDistance, y);
                circle.setCenterX(x + moveDistance);
              }
            }
          }
        });

    // Create a scene and place it in the stage
    primaryStage.setTitle("Exercise 15.11");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void startAnimation(double x, double y) {
    // Create translate transition
    TranslateTransition transition = new TranslateTransition(Duration.millis(180), circle);
    transition.setCycleCount(1);
    transition.setToX(x);
    transition.setToY(y);
    transition.setInterpolator(Interpolator.LINEAR);
    transition.play();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
