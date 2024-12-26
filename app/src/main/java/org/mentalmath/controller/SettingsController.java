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

public class SettingsController {

  @FXML Label textMain, textLang;
  @FXML Button bBack, bLangFR, bLangEN;

  @FXML public void initialize() {
    updateUI();

    bLangFR.setOnAction(e -> {
      ConfigManager.setProperty("language", "fr");
      ConfigManager.loadConfiguration();
      updateUI();
    });
    bLangEN.setOnAction(e -> {
      ConfigManager.setProperty("language", "en");
      ConfigManager.loadConfiguration();
      updateUI();
    });
  }

  private void updateUI() {
    Properties langProperties = ConfigManager.getCurrentLangProperties();
    textMain.setText(langProperties.getProperty("settingTextMain"));
    textLang.setText(langProperties.getProperty("settingTextLang"));
    bLangFR.setText(langProperties.getProperty("settingBLangFr"));
    bLangEN.setText(langProperties.getProperty("settingBLangEn"));
    bBack.setText(langProperties.getProperty("settingBBack"));
  }
  
  @FXML
    public void onBackClicked(ActionEvent event) {
        try {
            // Charger le fichier FXML pour l'interface de paramètres
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_menu.fxml"));
            Scene paramScene = new Scene(loader.load());
            
            // Obtenir la fenêtre principale (Stage) et changer la scène
            Stage primaryStage = (Stage) bBack.getScene().getWindow();
            primaryStage.setScene(paramScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
