package com.pckoala.javafx.chapter31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;

public class PathDemo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane pane = new Pane();

    // Create a path
    Path path = new Path(); // javafx.scene.shape.Path and not the alternative
    path.getElements().add(new MoveTo(50.0, 50.0));
    path.getElements().add(new HLineTo(150.5));
    path.getElements().add(new VLineTo(100.5));
    path.getElements().add(new LineTo(200.5, 150.5));

    ArcTo arcTo = new ArcTo(45, 45, 45, 250, 100.5, false, false);
    path.getElements().add(arcTo);
    path.getElements().add(new CubicCurveTo(250, 100, 350, 250, 450, 10));
    path.getElements().add(new ClosePath());

    pane.getChildren().add(path);
    path.setFill(null);
    Scene scene = new Scene(pane, 500, 250);
    primaryStage.setTitle("PathDemo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
