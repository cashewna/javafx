package com.pckoala.javafx.chapter31;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FXMLDocumentController {
  @FXML private TextField tfNumber1, tfNumber2, tfResult;

  @FXML
  private void addButtonAction() {
    tfResult.setText(getResult('+') + "");
  }

  @FXML
  private void subtractButtonAction() {
    tfResult.setText(getResult('-') + "");
  }

  @FXML
  private void multiplyButtonAction() {
    tfResult.setText(getResult('*') + "");
  }

  @FXML
  private void divideButtonAction() {
    tfResult.setText(getResult('/') + "");
  }

  private double getResult(char op) {
    double number1 = Double.parseDouble(tfNumber1.getText());
    double number2 = Double.parseDouble(tfNumber2.getText());
    switch (op) {
      case '+' -> {
        return number1 + number2;
      }
      case '-' -> {
        return number1 - number2;
      }
      case '*' -> {
        return number1 * number2;
      }
      case '/' -> {
        return number1 / number2;
      }
    }
    return Double.NaN;
  }
}
