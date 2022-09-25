package com.pckoala.javafx.chapter31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class StyleSheetDemo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    HBox hBox = new HBox(5);
    Scene scene = new Scene(hBox, 300, 250);
    scene.getStylesheets().add("./com.pckoala.javafx.chapter31/myStyle.css");

    Pane pane1 = new Pane();
    Circle circle1 = new Circle(50, 50, 30);
    Circle circle2 = new Circle(150, 50, 30);
    Circle circle3 = new Circle(100, 100, 30);
    pane1.getChildren().addAll(circle1, circle2, circle3);
    pane1.getStyleClass().add("border");

    circle1.getStyleClass().add("plainCircle");
    circle2.getStyleClass().add("plainCircle");
    circle3.setId("redCircle");

    Pane pane2 = new Pane();
    Circle circle4 = new Circle(100, 100, 30);
    circle4.getStyleClass().addAll("circleBorder", "plainCircle");
    circle4.setId("greenCircle");
    pane2.getChildren().add(circle4);
    pane2.getStyleClass().add("border");

    hBox.getChildren().addAll(pane1, pane2);

    primaryStage.setTitle("StyleSheetDemo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
