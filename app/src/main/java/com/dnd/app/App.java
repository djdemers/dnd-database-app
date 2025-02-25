package com.dnd.app;

import com.dnd.ui.DatabaseLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        boolean connected = showDatabaseLoginDialog();
        if (connected) {
            System.out.println("Database connection successful! Launching main menu if it was actually available");
            // TODO: Load the main application menu here
        } else {
            System.out.println("Connection failed or canceled by user.");
        }
    }

    /**
     * Shows the login dialog for database credentials.
     * @return true if login was successful, false if canceled or failed.
     */
    private boolean showDatabaseLoginDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/DatabaseLogin.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Database Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(loader.load());
            dialogStage.setScene(scene);

            // Get the controller and set up the stage
            DatabaseLoginController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();
            return controller.isConfirmed();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX application
    }
}
