package com.pckoala.javafx.tutorial6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FruitList extends Application {
  ObservableList<String> fruitList = FXCollections.observableArrayList();

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create menu items
    MenuBar menuBar = new MenuBar();
    Menu menuFile = new Menu("File");
    Menu menuHelp = new Menu("Help");

    menuBar.getMenus().addAll(menuFile, menuHelp);

    MenuItem load = new MenuItem("Load");
    MenuItem save = new MenuItem("Save");
    MenuItem close = new MenuItem("Close");

    menuFile.getItems().addAll(load, save, close);

    MenuItem about = new MenuItem("About");
    menuHelp.getItems().add(about);

    // Add menu to a VBox
    VBox root = new VBox();
    root.getChildren().add(menuBar);

    // Create a BorderPane
    BorderPane borderPane = new BorderPane();
    borderPane.setPadding(new Insets(5));

    // Create a btnAddFruit at the bottom-center of the borderPane
    Button btnAddFruit = new Button("Add Fruit");
    btnAddFruit.setPadding(new Insets(5, 15, 5, 15));
    borderPane.setBottom(btnAddFruit);
    BorderPane.setAlignment(btnAddFruit, Pos.CENTER);
    BorderPane.setMargin(btnAddFruit, new Insets(10));

    // Add borderPane to the root vBox pane
    root.getChildren().add(borderPane);

    // Text dialogue
    TextInputDialog textInputDialog = new TextInputDialog();
    textInputDialog.setHeaderText("Confirmation");
    textInputDialog.setTitle("Fruits");
    textInputDialog.setContentText("Enter a fruit:");

    // Event handlers
    btnAddFruit.setOnMouseClicked(
        event -> {
          Optional<String> result = textInputDialog.showAndWait();
          if (result.isPresent()) {
            fruitList.add(textInputDialog.getResult());
          }
        });

    // Create a ListView at the center of the borderPane
    ListView<String> listView = new ListView<>(fruitList);
    borderPane.setCenter(listView);

    // Menu > File > Save event handler
    save.setOnAction(
        event -> {
          FileChooser chooser = new FileChooser();
          chooser.setTitle("Save As");
          File selectedFile = chooser.showSaveDialog(primaryStage);
          if (selectedFile == null) { // no file chosen
            return;
          }

          // Convert observable list into an ArrayList for serialisation
          ArrayList<String> savedList = new ArrayList<>(fruitList);

          try {
            serialize(selectedFile, savedList);
          } catch (Exception e) {
            e.printStackTrace();
          }
        });

    close.setOnAction(event -> primaryStage.close());

    load.setOnAction(
        event -> {
          FileChooser chooser = new FileChooser();
          chooser.setTitle("Open Fruit File");
          File selectedFile = chooser.showOpenDialog(primaryStage);
          if (selectedFile == null) { // no file selected
            return;
          }
          ArrayList<?> savedList;
          try {
            savedList = deserialize(selectedFile);
            fruitList.clear();
            for (Object s : savedList) {
              fruitList.addAll((String) s);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        });

    about.setOnAction(
        event -> {
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("About");
          alert.setHeaderText(
              "This program was made with lots of love (and frustration and endless googling...) by me. ");
          alert.setContentText("\u00a9 Krishna Robles Khong 2022");
          alert.show();
        });

    Scene scene = new Scene(root, 400, 400);
    primaryStage.setTitle("Fruit List");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void serialize(File selectedFile, ArrayList<String> savedList) throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream(selectedFile);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    objectOutputStream.writeObject(savedList);
    objectOutputStream.close();
  }

  private ArrayList<?> deserialize(File selectedFile) throws IOException, ClassNotFoundException {
    FileInputStream fileInputStream = new FileInputStream(selectedFile);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    ArrayList<?> listOfFruits = (ArrayList<?>) objectInputStream.readObject();
    objectInputStream.close();
    return listOfFruits;
  }
}
