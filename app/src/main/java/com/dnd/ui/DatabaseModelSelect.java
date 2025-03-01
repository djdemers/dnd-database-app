package com.dnd.ui;

import com.dnd.utils.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Modality;
import com.dnd.ui.CRUDController;

public class DatabaseModelSelect {

    private Stage modelSelectionStage;
    private boolean confirmed = false;

    private CRUDController crudController = new CRUDController();

    public void setDialogStage(Stage stage) {
        this.modelSelectionStage = stage;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @FXML
    private void handleCharacterButton() throws IOException {
        System.out.println("Character button clicked");

        crudController.openCRUDStage();
    }

    @FXML
    private void handleItemButton() throws IOException  {
        System.out.println("Item button clicked");
        crudController.openCRUDStage();
    }

    @FXML
    private void handleItemStatsButton() throws IOException {
        System.out.println("Item stats button clicked");
        crudController.openCRUDStage();
    }

    @FXML
    private void handleLocationButton() throws IOException {
        System.out.println("Location button clicked");
        crudController.openCRUDStage();
    }   

    @FXML
    private void handleNotesButton() throws IOException {
        System.out.println("Notes button clicked");
        crudController.openCRUDStage();
    }   

    @FXML
    private void handleObjectivesButton() throws IOException {
        System.out.println("Objectives button clicked");
        crudController.openCRUDStage();
    }   

    @FXML
    private void handleQuestButton() throws IOException {
        System.out.println("Quest button clicked");
        crudController.openCRUDStage();
    }      

    @FXML
    private void handleSessionLogButton() throws IOException {
        System.out.println("Session log button clicked");
        crudController.openCRUDStage();
    }      

    @FXML
    private void handleCancel() {
        modelSelectionStage.close();
    }
    
}
