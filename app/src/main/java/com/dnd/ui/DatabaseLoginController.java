package com.dnd.ui;

import com.dnd.utils.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.io.IOException;

public class DatabaseLoginController {
    @FXML private TextField urlField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private Stage dialogStage;
    private boolean confirmed = false;

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @FXML
    private void handleConfirm() {
        String url = urlField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!url.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
            try {
                DatabaseConnector.setCredentials(url, username, password);
                DatabaseConnector.getConnection();  // Try connecting
                confirmed = true;
                dialogStage.close();

                openModelSelectionStage();
                

            } catch (Exception e) {
                System.out.println("Connection failed: " + e.getMessage());
                confirmed = false;
            }
        } else {
            System.out.println("All fields must be filled.");
        }
    }

    private void openModelSelectionStage() throws IOException {
        System.out.println("Opening model selection stage");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/DatabaseModelDisplay.fxml"));
            Stage modelSelectionStage = new Stage();
            System.out.println("Loader created" + loader);
            modelSelectionStage.setTitle("Model Selection");
            modelSelectionStage.initModality(Modality.WINDOW_MODAL);
            modelSelectionStage.initOwner(dialogStage);
            System.out.println("Stage initialized");
            Scene scene = new Scene(loader.load());
            System.out.println("Scene loaded");
            modelSelectionStage.setScene(scene);
            System.out.println("Scene set");
            modelSelectionStage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error loading model selection stage: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }



}
