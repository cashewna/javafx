package com.pckoala.javafx.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ResizableCircleRectangle extends Application {
  // Create a circle and a rectangle
  private final Circle circle = new Circle(60);
  private final Rectangle rectangle = new Rectangle(120, 120);

  private final StackPane pane = new StackPane();

  @Override
  public void start(Stage primaryStage) throws Exception {
    circle.setFill(Color.GRAY);
    rectangle.setFill(Color.WHITE);
    rectangle.setStroke(Color.BLACK);
    pane.getChildren().addAll(rectangle, circle);

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 140, 140);
    primaryStage.setTitle("Resizable Circle and Rectangle");
    primaryStage.setScene(scene);
    primaryStage.show();

    pane.widthProperty().addListener(observable -> resize());
    pane.heightProperty().addListener(observable -> resize());
  }

  private void resize() {
    double length = Math.min(pane.getWidth(), pane.getHeight());
    circle.setRadius(length / 2 - 15);
    rectangle.setHeight(length - 30);
    rectangle.setWidth(length - 30);
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
