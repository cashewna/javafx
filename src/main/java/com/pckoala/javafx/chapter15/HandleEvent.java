package com.pckoala.javafx.chapter15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HandleEvent extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a pane and set its properties
        HBox pane = new HBox(10);
        pane.setAlignment(Pos.CENTER);
        Button btOk = new Button("Ok.");
        Button btCancel = new Button("Cancel");
        OKHandlerClass handler1 = new OKHandlerClass(); // create handler
        btOk.setOnAction(handler1); // register handler
        CancelHandlerClass handler2 = new CancelHandlerClass();
        btCancel.setOnAction(handler2);
        pane.getChildren().addAll(btOk, btCancel);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Handle Event");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class OKHandlerClass implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Ok button clicked.");
        }
    }

    private class CancelHandlerClass implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Cancel button clicked.");
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
