package com.pckoala.javafx.chapter15;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Write a program that receives a string from the keyboard and displays it on the pane.
 * <em>Enter</em> key signals the end of the string and displays it on the pane. Whenever a new
 * string is entered, it is displayed on the pane.
 */
public class Exercise15_10 extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create a root gridPane
    GridPane rootPane = new GridPane();
    Scene scene = new Scene(rootPane, 400, 400);

    // Event handlers
    // Add input to variable on key type
    StringBuilder input = new StringBuilder();
    scene.setOnKeyTyped(event -> input.append(event.getCharacter()));

    // Add input to unique gridPane row on key Enter
    AtomicInteger rowCount = new AtomicInteger(0);
    scene.setOnKeyPressed(
        event -> {
          switch (event.getCode()) {
              // Display text on a new row
              // and clear the input
            case ENTER -> {
              Text displayInput = new Text(input.toString());
              rootPane.add(displayInput, 0, rowCount.getAndIncrement());
              input.setLength(0);
            }

              // Delete the previous character in the input
            case BACK_SPACE -> input.setLength(input.length() - 1);
          }
        });

    // Place scene in the stage
    primaryStage.setTitle("Exercise 15.10");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
