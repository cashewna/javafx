package com.pckoala.javafx.chapter15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LambdaHandlerDemo extends Application {
  @Override
  public void start(Stage primaryStage) {
    // Create text and add it to a pane
    Text text = new Text(40, 40, "Programming is fun.");
    Pane pane = new Pane(text);

    // Hold four buttons in an HBox
    Button btUp = new Button("Up");
    Button btDown = new Button("Down");
    Button btLeft = new Button("Left");
    Button btRight = new Button("Right");
    HBox hBox = new HBox(btUp, btDown, btLeft, btRight);
    hBox.setSpacing(10);
    hBox.setAlignment(Pos.CENTER);

    // Add pane and hBox to a BorderPane
    BorderPane borderPane = new BorderPane(pane);
    borderPane.setBottom(hBox);

    // Create and register the handler
    btUp.setOnAction(
        (ActionEvent e) -> {
          text.setY(text.getY() > 10 ? text.getY() - 5 : 10);
        });

    btDown.setOnAction(
        (e) -> {
          text.setY(text.getY() < pane.getHeight() ? text.getY() + 5 : pane.getHeight());
        });

    btLeft.setOnAction(
        e -> {
          text.setX(text.getX() > 0 ? text.getX() - 5 : 0);
        });

    btRight.setOnAction(
        e ->
            text.setX(
                text.getX() < pane.getWidth() - 100 ? text.getX() + 5 : pane.getWidth() - 100));

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 400, 350);
    primaryStage.setTitle("Lambda Anonymous Handler Demo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
