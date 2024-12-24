package org.mentalmath;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * - Lancement de l'application et chargement de l'interface principale
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ConfigManager.loadCustomConfiguration();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_menu.fxml")); //chargement depuis resources
        Scene scene = new Scene(loader.load());
        
        primaryStage.setTitle("Simple Mental Math App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
