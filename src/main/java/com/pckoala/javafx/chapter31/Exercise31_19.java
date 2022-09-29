package com.pckoala.javafx.chapter31;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise31_19 extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    StackPane pane1 = new StackPane();
    StackPane pane2 = new StackPane();
    StackPane pane3 = new StackPane();
    StackPane pane4 = new StackPane();

    Circle circle = new Circle(25);
    circle.setFill(Color.TRANSPARENT);
    circle.setStroke(Color.BLACK);
    pane1.getChildren().add(circle);
    pane1.setAlignment(Pos.CENTER);

    Arc arc = new Arc(0, 0, 20, 20, 90, 90);
    arc.setFill(Color.TRANSPARENT);
    arc.setStroke(Color.BLACK);
    arc.setType(ArcType.OPEN);
    pane2.getChildren().add(arc);
    pane2.setAlignment(Pos.CENTER);

    Rectangle rectangle = new Rectangle(20, 25);
    rectangle.setFill(Color.TRANSPARENT);
    rectangle.setStroke(Color.BLACK);
    pane3.getChildren().add(rectangle);
    pane3.setAlignment(Pos.CENTER);

    Ellipse ellipse = new Ellipse(22, 15);
    ellipse.setFill(Color.TRANSPARENT);
    ellipse.setStroke(Color.BLACK);
    pane4.getChildren().add(ellipse);
    pane4.setAlignment(Pos.CENTER);

    pane1.setPadding(new Insets(4, 15, 4, 15));
    pane2.setPadding(new Insets(4, 20, 4, 20));
    pane3.setPadding(new Insets(15, 35, 15, 35));
    pane4.setPadding(new Insets(4, 20, 4, 20));

    SplitPane splitPane1 = new SplitPane(pane1, pane2);
    SplitPane splitPane2 = new SplitPane(pane3, pane4);

    splitPane1.setDividerPositions(0.5f, 0.5f);
    splitPane2.setDividerPositions(0.5f, 0.5f);

    VBox vBox = new VBox();
    vBox.getChildren().addAll(splitPane1, splitPane2);

    Scene scene = new Scene(vBox, 250, vBox.getMinHeight());
    primaryStage.setTitle("Exercise 31_19");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
