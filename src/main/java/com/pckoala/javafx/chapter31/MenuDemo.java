package com.pckoala.javafx.chapter31;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 *
 * <ol>
 *   <li>Create a menu bar and add it into a vBox.
 *       <ul>
 *         <li>Create the menus Operation and Exit and add them to menu bar.
 *         <li>Add the sub-menu items to Operation menu - Add, Subtract, Multiply, Divide.
 *         <li>Add the sub-menu items to Exit menu - Close.
 *       </ul>
 *   <li>Create an HBox to hold labels and text fields and place it into vBox.
 *   <li>Create an HBox to hold the four buttons labeled Add, Subtract, Multiply, Divide, and place
 *       them inside a VBox
 *   <li>Implement the handlers to process the events from the menu items and the buttons
 * </ol>
 */
public class MenuDemo extends Application {
  private final TextField tfNumber1 = new TextField();
  private final TextField tfNumber2 = new TextField();
  private final TextField tfResult = new TextField();

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    MenuBar menuBar = new MenuBar();

    Menu menuOperation = new Menu("Operation");
    Menu menuExit = new Menu("Exit");
    menuBar.getMenus().addAll(menuOperation, menuExit);

    MenuItem menuItemAdd = new MenuItem("Add");
    MenuItem menuItemSubtract = new MenuItem("Subtract");
    MenuItem menuItemMultiply = new MenuItem("Multiply");
    MenuItem menuItemDivide = new MenuItem("Divide");
    menuOperation
        .getItems()
        .addAll(menuItemAdd, menuItemSubtract, menuItemMultiply, menuItemDivide);

    MenuItem menuItemClose = new MenuItem("Close");
    menuExit.getItems().add(menuItemClose);

    menuItemAdd.setAccelerator(KeyCombination.keyCombination("\u2318 A"));
    menuItemSubtract.setAccelerator(KeyCombination.keyCombination("\u2318 S"));
    menuItemMultiply.setAccelerator(KeyCombination.keyCombination("\u2318 M"));
    menuItemDivide.setAccelerator(KeyCombination.keyCombination("\u2318 D"));

    HBox hBox1 = new HBox(5);
    tfNumber1.setPrefColumnCount(2);
    tfNumber2.setPrefColumnCount(2);
    tfResult.setPrefColumnCount(2);
    hBox1
        .getChildren()
        .addAll(
            new Label("Number 1:"),
            tfNumber1,
            new Label("Number 2:"),
            tfNumber2,
            new Label("Result:"),
            tfResult);
    hBox1.setAlignment(Pos.CENTER);

    HBox hBox2 = new HBox(5);
    Button btAdd = new Button("Add");
    Button btSubtract = new Button("Subtract");
    Button btMultiply = new Button("Multiply");
    Button btDivide = new Button("Divide");
    hBox2.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide);
    hBox2.setAlignment(Pos.CENTER);

    VBox vBox = new VBox(10);
    vBox.getChildren().addAll(menuBar, hBox1, hBox2);
    Scene scene = new Scene(vBox, 300, 250);
    primaryStage.setTitle("MenuDemo");
    primaryStage.setScene(scene);
    primaryStage.show();

    // Handle menu actions
    menuItemAdd.setOnAction(event -> perform('+'));
    menuItemSubtract.setOnAction(event -> perform('-'));
    menuItemMultiply.setOnAction(event -> perform('*'));
    menuItemDivide.setOnAction(event -> perform('/'));

    // Handle button actions
    btAdd.setOnAction(event -> perform('+'));
    btSubtract.setOnAction(event -> perform('-'));
    btMultiply.setOnAction(event -> perform('*'));
    btDivide.setOnAction(event -> perform('/'));
  }

  private void perform(char operator) {
    double number1 = 0, number2 = 0;
    if (!tfNumber1.getText().isEmpty() || !tfNumber2.getText().isEmpty()) {
      number1 = Double.parseDouble(tfNumber1.getText());
      number2 = Double.parseDouble(tfNumber2.getText());
    }

    double result =
        switch (operator) {
          case '+' -> number1 + number2;
          case '-' -> number1 - number2;
          case '*' -> number1 * number2;
          case '/' -> number1 / number2;
          default -> 0;
        };

    tfResult.setText(result + "");
  }
}
