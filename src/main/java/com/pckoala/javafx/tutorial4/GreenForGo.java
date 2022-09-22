package com.pckoala.javafx.tutorial4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GreenForGo extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create a circle and a label and add it to a StackPane
    StackPane pane = new StackPane();
    Circle circle = new Circle(100);
    circle.setFill(Color.GREEN);
    Text text = new Text("GO");
    text.setFont(Font.font("arial", 50));
    text.setFill(Color.WHITE);
    pane.getChildren().addAll(circle, text);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 400);
    primaryStage.setTitle("Green for Go");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
