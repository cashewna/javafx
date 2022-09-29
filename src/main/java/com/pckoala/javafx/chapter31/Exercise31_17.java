package com.pckoala.javafx.chapter31;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Create an investment value calculator. <br>
 * Write a program that calculates the future value of an investment <br>
 * at a given interest rate for a specified number of years. <br>
 * The formula for the calculation is as follows: <br>
 * <code>futureValue = investmentAmount * (1 + monthlyInterestRate)^(years * 12)</code>
 */
public class Exercise31_17 extends Application {

  private final TextField investmentAmount = new TextField();
  private final TextField numberOfYears = new TextField();
  private final TextField annualInterestRate = new TextField();
  private final TextField futureValue = new TextField();

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create menuBar and add menuOperation to it
    MenuBar menuBar = new MenuBar();
    Menu menuOperation = new Menu("Operation");
    menuBar.getMenus().add(menuOperation);

    // Add menuCalculate to menuOperation
    Menu menuCalculate = new Menu("Calculate");
    menuOperation.getItems().add(menuCalculate);

    // Create gridPane
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Investment amount:"), 0, 0);
    gridPane.add(investmentAmount, 1, 0);
    gridPane.add(new Label("Number of years:"), 0, 1);
    gridPane.add(numberOfYears, 1, 1);
    gridPane.add(new Label("Annual Interest Rate:"), 0, 2);
    gridPane.add(annualInterestRate, 1, 2);
    gridPane.add(new Label("Future value:"), 0, 3);
    gridPane.add(futureValue, 1, 3);
    // Create calculate button and add it to the gridPane
    Button btnCalculate = new Button("Calculate"); // initialise new variable for event handler
    gridPane.add(btnCalculate, 1, 4);

    // Set gridPane properties
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(5);
    gridPane.setHgap(5);
    gridPane.setPadding(new Insets(10));
    futureValue.setEditable(false);

    VBox vBox = new VBox();
    vBox.getChildren().addAll(menuBar, gridPane);

    Scene scene = new Scene(vBox, 350, 200);
    primaryStage.setTitle("Exercise 31.17");
    primaryStage.setScene(scene);
    primaryStage.show();

    // Create event handlers
    btnCalculate.setOnMouseClicked(
        event -> calculateFutureValue(investmentAmount, numberOfYears, annualInterestRate));
  }

  private void calculateFutureValue(
      TextField investmentAmount, TextField numberOfYears, TextField annualInterestRate) {
    // Convert TextFields to strings and remove any characters that are not a dot or a digit
    double amount = Double.parseDouble(investmentAmount.getText().replaceAll("[^\\d.]", ""));
    double years = Double.parseDouble(numberOfYears.getText().replaceAll("[^\\d.]", ""));
    double interestRate =
        Double.parseDouble(annualInterestRate.getText().replaceAll("[^\\d.]", ""));
    double monthlyInterestRate = interestRate / 1200;

    // Return the result and format the TextField values
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
    futureValue.setText(
        numberFormat.format(amount * Math.pow((1 + monthlyInterestRate), (years) * 12)));
    investmentAmount.setText(numberFormat.format(amount));
    annualInterestRate.setText(String.format("%.2f%%", interestRate));
  }
}
