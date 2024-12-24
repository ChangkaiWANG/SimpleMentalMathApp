package org.mentalmath.controller;

import java.io.IOException;
import java.util.Properties;

import org.mentalmath.ConfigManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameController {
  
  @FXML private Label textMain, textMsg, textQuestion;
  @FXML private TextField textAnswer;
  @FXML private Button bBack;

  private int result;

  @FXML public void initialize() {
    updateUI();
    result = generateQuestion();
    textMsg.setText("");

    bBack.setOnAction(e -> {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_menu.fxml"));
        Scene paramScene = new Scene(loader.load());
        Stage primaryStage = (Stage) bBack.getScene().getWindow();
        primaryStage.setScene(paramScene);
      } catch (IOException ex) {
          ex.printStackTrace();
      }
    });
    textAnswer.setOnAction(e -> checkAnswerAndGenerateNextQuestion());
  }

  private void updateUI() {
    Properties langPropreties = ConfigManager.getCurrentLangProperties();
    textMain.setText(langPropreties.getProperty("gameTextMain"));
    bBack.setText(langPropreties.getProperty("settingBBack"));
  }

  private int generateQuestion() {
    int a = (int) (Math.random() * 10);
    int b = (int) (Math.random() * 10);
    String operator = ConfigManager.getProperty("operator");
    if (operator.equals("/")) {
      b = (int) (Math.random() * 9) + 1; // Évite la division par zéro
      int res = a * b;
      a = res;
    }
    textQuestion.setText(a + " " + operator + " " + b + " = ");
    textAnswer.clear();
    return calculate(a, b, operator);
  }

  private int calculate(int a, int b, String operator) {
    return switch (operator) {
        case "+" -> a + b;
        case "-" -> a - b;
        case "*" -> a * b;
        case "/" -> b != 0 ? a / b : 0; // Évite la division par zéro
        default -> 0;
    };
  }

  private void checkAnswerAndGenerateNextQuestion() {
    try {
      int userAnswer = Integer.parseInt(textAnswer.getText());
      if (userAnswer == result) {
        textMsg.setText("Correct !");
      } else {
        textMsg.setText("Incorrect !");
      }
    } catch (NumberFormatException e) {
      textMsg.setText(ConfigManager.getCurrentLangProperties().getProperty("game_error_msg"));
    }
    result = generateQuestion(); 
  }

}
