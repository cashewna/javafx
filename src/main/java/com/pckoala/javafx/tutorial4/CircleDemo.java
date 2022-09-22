package com.pckoala.javafx.tutorial4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class CircleDemo extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create a pane and the green and red circles
    Pane pane = new Pane();
    Circle circleRed = new Circle(100, 90, 15);
    Circle circleGreen = new Circle(240, 239, 15);
    circleRed.setFill(Color.RED);
    circleGreen.setFill(Color.GREEN);

    // Create a line connecting the center of the two circles
    Line line =
        new Line(
            circleRed.getCenterX(),
            circleRed.getCenterY(),
            circleGreen.getCenterX(),
            circleGreen.getCenterY());


    // Add a label calculating the length of the line
    // hypotenuse = sqrt(a^2 + b^2)
    double startX = line.getStartX();
    double endX = line.getEndX();
    double startY = line.getStartY();
    double endY = line.getEndY();
    Label label = new Label(
        Double.toString(Math.sqrt(Math.pow((endX - startX), 2) + Math.pow((endY - startY), 2)))
    );

    label.setLayoutX(endX - startX + 20);
    label.setLayoutY(endY - startY - 10);

    // Add the circles and the line to the pane
    pane.getChildren().addAll(circleRed, circleGreen, line, label);

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
