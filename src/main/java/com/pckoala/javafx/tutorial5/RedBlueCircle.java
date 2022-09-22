package com.pckoala.javafx.tutorial5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RedBlueCircle extends Application {
  private Circle circle;

  @Override
  public void start(Stage primaryStage) {
    // Create a pane
    Pane pane = new Pane();
    Scene scene = new Scene(pane, 200, 400);

    // When scene event onPress, create red circle
    scene.setOnMousePressed(
        (MouseEvent event) -> {
          double x = event.getX();
          double y = event.getY();
          circle = new Circle(x, y, 50);
          circle.setFill(Color.RED);
          pane.getChildren().add(circle);
        });

    // When mouse release, change circle colour to blue
    scene.setOnMouseReleased(event -> circle.setFill(Color.BLUE));

    // Place scene on stage
    primaryStage.setTitle("Red Blue Circle");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
