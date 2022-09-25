package com.pckoala.javafx.chapter31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CurveDemo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane pane = new Pane();

    // Create a quadratic curve
    QuadCurve quadCurve = new QuadCurve(10, 80, 40, 20, 150, 56);
    quadCurve.setFill(Color.WHITE);
    quadCurve.setStroke(Color.BLACK);

    pane.getChildren()
        .addAll(quadCurve, new Circle(40, 20, 6), new Text(40 + 5, 20 - 5, "Control Point"));

    // Create a cubic curve
    CubicCurve cubicCurve = new CubicCurve(200, 80, 240, 20, 350, 156, 450, 80);
    cubicCurve.setFill(Color.WHITE);
    cubicCurve.setStroke(Color.BLACK);

    pane.getChildren()
        .addAll(
            cubicCurve,
            new Circle(240, 20, 6),
            new Text(240 + 5, 20 - 5, "Control Point 1"),
            new Circle(350, 156, 6),
            new Text(350 + 5, 156 - 5, "Control Point 2"));

    Scene scene = new Scene(pane, 460, 180);
    primaryStage.setTitle("CurveDemo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
