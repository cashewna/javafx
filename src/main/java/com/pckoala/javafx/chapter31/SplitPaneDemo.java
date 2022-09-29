package com.pckoala.javafx.chapter31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplitPaneDemo extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    SplitPane splitPane = new SplitPane();
    splitPane.setDividerPositions(0.45, 0.556, 0.99); // dividing ratios absolute positioning

    // Create buttons for layout using absolute positioning
    Button forwardButton = new Button(">>");
    Button backwardButton = new Button("<<");
    forwardButton.setLayoutX(1);
    forwardButton.setLayoutY(150);
    backwardButton.setLayoutX(1);
    backwardButton.setLayoutY(200);

    // Add the buttons to a pane and set the pane to a VBox
    Pane pane = new Pane();
    pane.getChildren().addAll(forwardButton, backwardButton);
    VBox middleBox = new VBox();
    middleBox.getChildren().add(pane);

    // Create two list views and add some strings
    ListView<String> listLeft = new ListView<>();
    listLeft.getItems().addAll("Item 1", "Item 2", "Item 3", "Item 4");
    ListView<String> listRight = new ListView<>();
    listRight.getItems().addAll("Item 5", "Item 6");

    // Add the lists and VBox to the split pane areas
    splitPane.getItems().addAll(listLeft, middleBox, listRight);

    Scene scene = new Scene(splitPane, 400, 400);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Split Pane Demo");
    primaryStage.show();

    // Event handlers for moving list around
    forwardButton.setOnMouseClicked(
        event -> {
          int lastItem = getLastListItem(listLeft);
          listRight.getItems().add(listLeft.getItems().get(lastItem));
          listLeft.getItems().remove(lastItem);
        });

    backwardButton.setOnMouseClicked(
        event -> {
          int lastItem = getLastListItem(listRight);
          listLeft.getItems().add(listRight.getItems().get(lastItem));
          listRight.getItems().remove(lastItem);
        });
  }

  private int getLastListItem(ListView<String> list) {
    return list.getItems().size() - 1;
  }
}
