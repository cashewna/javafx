package com.pckoala.javafx.chapter31;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise31_01 extends Application {
  ArrayList<Pane> subPane = new ArrayList<>();

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane pane = new Pane();
    HBox hBox = new HBox(20);
    hBox.setPadding(new Insets(0, 80, 0, 80));
    Scene scene = new Scene(pane);
    scene.getStylesheets().add("./com.pckoala.javafx.chapter31/Exercise31_01_style.css");

    for (int i = 0; i < 4; i++) {
      StackPane stackPane = new StackPane();
      Rectangle rectangle = new Rectangle(40, 50);
      rectangle.setFill(Color.TRANSPARENT);

      Circle circle = new Circle(20);

      switch (i) {
        case 0 -> {
          rectangle.getStyleClass().addAll("black-stroke", "stroke__width-lg");
          circle.getStyleClass().addAll("black-stroke", "white-fill");
        }
        case 1 -> {
          circle.getStyleClass().add("white-fill");
          circle.setId("red-stroke");
        }

        case 2 -> circle.setId("green-fill");

        case 3 -> {
          rectangle.setId("red-stroke");
          rectangle.getStyleClass().add("stroke__width-lg");
        }
      }

      stackPane.getChildren().addAll(circle, rectangle);
      StackPane.setAlignment(circle, Pos.CENTER);
      subPane.add(stackPane);
    }

    // Display each StackPane in the HBox
    for (Pane i : subPane) {
      hBox.getChildren().add(i);
    }

    pane.getChildren().add(hBox);
    primaryStage.setTitle("Exercise 31.1");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
