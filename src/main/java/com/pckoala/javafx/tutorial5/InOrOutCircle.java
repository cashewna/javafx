package com.pckoala.javafx.tutorial5;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class InOrOutCircle extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create a border pane
    BorderPane pane = new BorderPane();
    Circle circle = new Circle(100, 60, 50);
    circle.setFill(Color.BLUE);
    pane.setCenter(circle);

    // Create label
    Label label = new Label();
    pane.setBottom(label);
    BorderPane.setAlignment(label, Pos.BASELINE_CENTER);

    // Event handlers
    circle.setOnMouseEntered(event -> label.setText("Mouse entered."));
    circle.setOnMouseExited(event -> label.setText("Mouse exited."));

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 250);
    primaryStage.setTitle("In Or Out Circle");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
