package com.pckoala.javafx.chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/** Display the mousePosition on mousePressed and remove display on mouseRelease */
public class Exercise15_08 extends Application {
  // Define variables
  Label labelMousePos = new Label();

  @Override
  public void start(Stage primaryStage) {
    // Create a root pane and scene
    Pane rootPane = new Pane();
    Scene scene = new Scene(rootPane);

    // Event handle mousePress in scene
    // and create a label of the mouse position
    scene.setOnMousePressed(
        event -> {
          double x = event.getX();
          double y = event.getY();
          labelMousePos.setText(String.format("(%.1f, %.1f)", x, y));
          labelMousePos.setLayoutX(x);
          labelMousePos.setLayoutY(y);
          rootPane.getChildren().add(labelMousePos);
        });

    // Event handle mouseRelease
    // remove label
    scene.setOnMouseReleased(event -> rootPane.getChildren().remove(labelMousePos));

    // Place scene in the stage
    primaryStage.setTitle("Exercise 15.8");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
