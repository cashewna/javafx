package com.pckoala.javafx.chapter14;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DisplayClock extends Application {
    @Override
    public void start(Stage primaryStage) {
        ClockPane clock = new ClockPane();
        String timeString = clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond();
        Label labelCurrentTime = new Label(timeString);

        // Place clock and label in border pane
        BorderPane pane = new BorderPane();
        pane.setCenter(clock);
        pane.setBottom(labelCurrentTime);
        BorderPane.setAlignment(labelCurrentTime, Pos.TOP_CENTER);

        // Create a scene and place it on stage
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("Display Clock");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
