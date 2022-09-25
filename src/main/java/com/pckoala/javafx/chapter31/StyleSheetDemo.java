package com.pckoala.javafx.chapter31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StyleSheetDemo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    HBox hBox = new HBox(5);
    Scene scene = new Scene(hBox, 300, 250);
    scene.getStylesheets().add("myStyle.css");

  }
}
