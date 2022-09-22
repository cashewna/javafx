package com.pckoala.javafx.chapter15;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/** Moves a rectangle along the outline of a circle */
public class PathTransitionDemo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create a pane
    Pane pane = new Pane();

    // Create a rectangle
    Rectangle rectangle = new Rectangle(0, 0, 25, 50);
    rectangle.setFill(Color.ORANGE);

    // Create a circle
    Circle circle = new Circle(125, 100, 50);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);

    // Add circle and rectangle to the pane
    pane.getChildren().addAll(circle, rectangle);

    // Create a path transition
    PathTransition pathTransition = new PathTransition();
    pathTransition.setDuration(Duration.millis(1000));
    pathTransition.setPath(circle);
    pathTransition.setNode(rectangle);
    pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
    pathTransition.setCycleCount(Timeline.INDEFINITE);
    pathTransition.setAutoReverse(true);
    pathTransition.play(); // Start animation

    circle.setOnMousePressed(event -> pathTransition.pause());
    circle.setOnMouseReleased(event -> pathTransition.play());

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 200);
    primaryStage.setTitle("PathTransitionDemo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
