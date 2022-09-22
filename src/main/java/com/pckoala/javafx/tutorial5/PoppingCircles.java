package com.pckoala.javafx.tutorial5;

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PoppingCircles extends Application {
  @Override
  public void start(Stage primaryStage) {
    // Import sound file
    String soundFile = "pop.mp3";
    Media sound = new Media(new File(soundFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    Pane pane = new Pane();
    ArrayList<Circle> circles = new ArrayList<>();

    // Populate the array list with circles
    for (int i = 0; i < 10; i++) {
      Circle c = new Circle(10);
      c.setOnMouseClicked(
          event -> {
            mediaPlayer.stop();
            c.setVisible(false);
            mediaPlayer.play();
          });
      circles.add(c);
    }

    // Display the circles in the pane at random locations
    for (Circle circle : circles) {
      int x = (int) (10 + Math.random() * 390);
      int y = (int) (10 + Math.random() * 390);
      System.out.println(x + ", " + y);

      circle.setCenterX(x);
      circle.setCenterY(y);

      // Create random RGB values
      int r = (int) (Math.random() * 255);
      int g = (int) (Math.random() * 255);
      int b = (int) (Math.random() * 255);

      circle.setFill(Color.rgb(r, g, b));
    }

    pane.getChildren().addAll(circles);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 400);
    primaryStage.setTitle("Popping Circles");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
