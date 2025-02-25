package com.dnd.ui;

import com.dnd.utils.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            } catch (Exception e) {
                System.out.println("Connection failed: " + e.getMessage());
                confirmed = false;
            }
        } else {
            System.out.println("All fields must be filled.");
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
