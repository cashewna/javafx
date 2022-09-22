package com.pckoala.javafx.tutorial5;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DragRectangle extends Application {

  private double x = 200f;
  private double y = 200f;

  @Override
  public void start(Stage primaryStage) {
    // Create a pane and a scene
    Pane pane = new Pane();
    Scene scene = new Scene(pane, 400, 400);

    // Create a rectangle and add it to the pane
    int rectWidth = 70;
    int rectHeight = 30;
    Rectangle rectangle =
        new Rectangle(x - (rectWidth / 2.0), y - (rectHeight / 2.0), rectWidth, rectHeight);
    rectangle.setFill(Color.ORANGE);
    rectangle.setStroke(Color.BLACK);
    pane.getChildren().add(rectangle);

    // Create event handlers
    rectangle.setOnMousePressed(event -> scene.setCursor(Cursor.CLOSED_HAND));

    rectangle.setOnMouseReleased(event -> scene.setCursor(Cursor.DEFAULT));

    rectangle.setOnMouseDragged(
        event -> {
          // Get the offset of the drag
          double offsetX = event.getX() - x;
          double offsetY = event.getY() - y;
          rectangle.setY(rectangle.getY() + offsetY);
          rectangle.setX(rectangle.getX() + offsetX);

          x = event.getX();
          y = event.getY();
        });

    primaryStage.setTitle("Drag Rectangle");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
