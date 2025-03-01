package com.dnd.ui;

import com.dnd.utils.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class DatabaseModelSelect {

    private Stage modelSelectionStage;
    private boolean confirmed = false;

    public void setDialogStage(Stage stage) {
        this.modelSelectionStage = stage;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

        @FXML
    private void handleCharacterButton() {
        System.out.println("Character button clicked");
        // TODO: Implement character button functionality
    }

    @FXML
    private void handleItemButton() {
        System.out.println("Item button clicked");
        // TODO: Implement item button functionality
    }

    @FXML
    private void handleItemStatsButton() {
        System.out.println("Item stats button clicked");
        // TODO: Implement item stats button functionality
    }

    @FXML
    private void handleLocationButton() {
        System.out.println("Location button clicked");
        // TODO: Implement location button functionality
    }   

    @FXML
    private void handleNotesButton() {
        System.out.println("Notes button clicked");
        // TODO: Implement notes button functionality
    }   

    @FXML
    private void handleObjectivesButton() {
        System.out.println("Objectives button clicked");
        // TODO: Implement objectives button functionality
    }   

    @FXML
    private void handleQuestButton() {
        System.out.println("Quest button clicked");
        // TODO: Implement quest button functionality
    }      

    @FXML
    private void handleSessionLogButton() {
        System.out.println("Session log button clicked");
        // TODO: Implement session log button functionality
    }      

    @FXML
    private void handleCancel() {
        modelSelectionStage.close();
    }
    
}
