package org.mentalmath.controller;

import java.io.IOException;
import java.util.Properties;

import org.mentalmath.ConfigManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML private Label textMain, textMsg;
    @FXML private Button bAdd, bSub, bMul, bDiv, bParam, bQuit;

    @FXML
    public void initialize() {
      updateUI(); 
      bQuit.setOnAction(e -> System.exit(0));
    }

    private void updateUI() {
      Properties langPropreties = ConfigManager.getCurrentLangProperties();
      textMain.setText(langPropreties.getProperty("textMain"));
      bAdd.setText(langPropreties.getProperty("bAddition"));
      bSub.setText(langPropreties.getProperty("bSubstraction"));
      bMul.setText(langPropreties.getProperty("bMultiplication"));
      bDiv.setText(langPropreties.getProperty("bDivision"));
      bQuit.setText(langPropreties.getProperty("bQuit"));
    }

    @FXML
    public void onAdditionClicked(ActionEvent event) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Scene paramScene = new Scene(loader.load());
        
        // Obtenir la fenêtre principale (Stage) et changer la scène
        Stage primaryStage = (Stage) bParam.getScene().getWindow();
        primaryStage.setScene(paramScene);
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    @FXML
    public void onSubtractionClicked(ActionEvent event) {
      if (!verifConfig()) return;
      try {
        ConfigManager.setProperty("operator", "-");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Scene paramScene = new Scene(loader.load());
        
        // Obtenir la fenêtre principale (Stage) et changer la scène
        
        Stage primaryStage = (Stage) bParam.getScene().getWindow();
        primaryStage.setScene(paramScene);
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    @FXML
    public void onMultiplicationClicked(ActionEvent event) {
      if (!verifConfig()) return;
      try {
        ConfigManager.setProperty("operator", "*");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Scene paramScene = new Scene(loader.load());
        
        // Obtenir la fenêtre principale (Stage) et changer la scène
        
        Stage primaryStage = (Stage) bParam.getScene().getWindow();
        primaryStage.setScene(paramScene);
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    @FXML
    public void onDivisionClicked(ActionEvent event) {
      if (!verifConfig()) return;
      try {
        ConfigManager.setProperty("operator", "/");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
        Scene paramScene = new Scene(loader.load());
        
        // Obtenir la fenêtre principale (Stage) et changer la scène
        
        Stage primaryStage = (Stage) bParam.getScene().getWindow();
        primaryStage.setScene(paramScene);
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    private boolean verifConfig() {
      boolean isPresent = ConfigManager.loadCustomConfigFile();
      if (!isPresent) {
        textMsg.setText(ConfigManager.getCurrentLangProperties().getProperty("main_menu_error_msg"));
        textMsg.setVisible(true);
      }
      return isPresent;
    }

    @FXML
    public void onSettingsClicked(ActionEvent event) {
      try {
          // Charger le fichier FXML pour l'interface de paramètres
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/settings.fxml"));
          Scene paramScene = new Scene(loader.load());
          
          // Obtenir la fenêtre principale (Stage) et changer la scène
          Stage primaryStage = (Stage) bParam.getScene().getWindow();
          primaryStage.setScene(paramScene);
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
}
