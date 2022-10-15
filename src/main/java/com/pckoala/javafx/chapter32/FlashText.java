package com.pckoala.javafx.chapter32;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FlashText extends Application {
  private String text = "";

  @Override
  public void start(Stage primaryStage) throws Exception {
    StackPane pane = new StackPane();
    Label lblText = new Label("Programming is fun.");
    pane.getChildren().add(lblText);

    new Thread(
        () -> {
          try {
            while (true) {
              text = lblText.getText().trim().length() == 0 ? "Welcome." : "";

              // Run from JavaFX GUI
              Platform.runLater(
                  () -> lblText.setText(text));

              Thread.sleep(200);
            }
          } catch (InterruptedException e) {
            throw new RuntimeException("InterruptedException", e);
          }
        })
        .start();

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 50);
    primaryStage.setTitle("FlashText");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
