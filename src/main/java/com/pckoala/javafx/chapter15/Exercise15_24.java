package com.pckoala.javafx.chapter15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/** Animate a pendulum swing. Pause/resume animation on mouse press/release. */
public class Exercise15_24 extends Application {

  private static final double WIDTH = 400;
  private static final double HEIGHT = 400;

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create a scene and place it in the stage
    Pane pane = new Pane();

    // Create an arc and set its properties
    Arc arc = new Arc(WIDTH / 2, HEIGHT * 0.6, WIDTH * 0.4, HEIGHT * 0.2, 220, 100);
    arc.setFill(Color.TRANSPARENT);
    arc.setStroke(Color.BLACK);
    arc.setType(ArcType.OPEN);

    // Create a circle and set its properties
    // Add circle to pane
    double sinX =
        Math.sin(50 * Math.PI / 180); // thirdCircleQuadrant(270) - arcStartAngle(220) = 50
    double x = arc.getRadiusX() * sinX;
    double cosY = Math.cos(50 * Math.PI / 180);
    double y = arc.getRadiusY() * cosY;
    Circle circle = new Circle(arc.getCenterX() - x, arc.getCenterY() + y, 10);
    circle.setFill(Color.MIDNIGHTBLUE);
    pane.getChildren().addAll(arc, circle);

    // Transition
    PathTransition path = new PathTransition();
    path.setDuration(Duration.millis(700));
    path.setPath(arc);
    path.setNode(circle);
    path.setCycleCount(Timeline.INDEFINITE);
    path.setAutoReverse(true);
    path.play();

    // Pendulum event handler
    pane.setOnMousePressed(event -> path.pause());
    pane.setOnMouseReleased(event -> path.play());

    Scene scene = new Scene(pane, WIDTH, HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Exercise 15.24");
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
