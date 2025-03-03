package com.dnd.app;

import com.dnd.model.DNDCharacter;
import com.dnd.ui.CharacterFormController;
import com.dnd.ui.DatabaseLoginController;
import com.dnd.ui.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showDatabaseLoginDialog();
    }

    public void showDatabaseLoginDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/DatabaseLogin.fxml"));
            Scene scene = new Scene(loader.load());

            // Set controller reference
            DatabaseLoginController controller = loader.getController();
            controller.setApp(this);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Database Login");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/MainMenu.fxml"));
            Scene scene = new Scene(loader.load());

            // Set controller reference
            MainMenuController controller = loader.getController();
            controller.setApp(this); // Allows navigation back to login

            primaryStage.setScene(scene);
            primaryStage.setTitle("D&D Database - Main Menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCharacterForm(DNDCharacter character) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/CharacterForm.fxml"));
            Scene scene = new Scene(loader.load());

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Character Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            CharacterFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCharacter(character);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

