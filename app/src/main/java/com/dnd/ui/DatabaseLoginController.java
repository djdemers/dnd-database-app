package com.dnd.ui;

import com.dnd.app.App;
import com.dnd.utils.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class DatabaseLoginController {
    @FXML private TextField urlField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private App app; // Reference to App for navigation

    public void setApp(App app) {
        this.app = app;
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
                app.showMainMenu();  // Switch to Main Menu on success
            } catch (Exception e) {
                System.out.println(" Connection failed: " + e.getMessage());
            }
        } else {
            System.out.println(" All fields must be filled.");
        }
    }

    @FXML
    private void handleCancel() {
        System.exit(0); //  Close the application
    }
}

