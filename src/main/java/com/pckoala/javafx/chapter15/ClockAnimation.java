package com.pckoala.javafx.chapter15;

import com.pckoala.javafx.chapter14.ClockPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Every 1 second, paint the current time
 */
public class ClockAnimation extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    ClockPane clock = new ClockPane(); // Create a clock using chapter14 ClockPane

    // Create a handler for animation
    EventHandler<ActionEvent> eventHandler = event -> clock.setCurrentTime();

    // Create an animation for a running clock
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();

    // Create a scene and place it in the stage
    Scene scene = new Scene(clock, 250, 250);
    primaryStage.setTitle("Clock Animation");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
