package com.pckoala.javafx.chapter15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ControlCircle extends Application {

    private final CirclePane circlePane = new CirclePane();

    @Override
    public void start(Stage primaryStage) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btEnlarge = new Button("Enlarge");
        Button btShrink = new Button("Shrink");
        hBox.getChildren().add(btEnlarge);
        hBox.getChildren().add(btShrink);

        btEnlarge.setOnAction(new EnlargeHandler());
        btShrink.setOnAction(new ShrinkHandler());

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(circlePane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 200, 150);
        primaryStage.setTitle("Control Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class CirclePane extends StackPane {
        private final Circle circle = new Circle(50);

        public CirclePane() {
            getChildren().add(circle);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.WHITE);
        }

        public void enlarge() {
            circle.setRadius(circle.getRadius() + 2);
        }

        // If circle radius is less than 2, don't shrink
        public void shrink() {
            circle.setRadius(circle.getRadius() > 2 ? circle.getRadius() - 2 : circle.getRadius());
        }
    }

    private class EnlargeHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            circlePane.enlarge();
        }
    }

    private class ShrinkHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            circlePane.shrink();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}
