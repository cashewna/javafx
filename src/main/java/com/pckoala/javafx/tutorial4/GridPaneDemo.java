package com.pckoala.javafx.tutorial4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GridPaneDemo extends Application {

  @Override
  public void start(Stage primaryStage) {
    GridPane pane = new GridPane();

    Color color;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        Rectangle square = new Rectangle(50, 50);
        color = (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE; // alternate the colours
        square.setFill(color);
        pane.add(square, i, j);
      }
    }

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("GridPane Demo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
