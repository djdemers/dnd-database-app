package com.dnd.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Scene;

public class CRUDController {
    @FXML
    private Button createButton;

    @FXML
    private Button readButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;


    private Stage crudStage;

    public void setCRUDStage(Stage stage) {
        this.crudStage = stage;
    }

    @FXML
    private void handleCreate() {   
        System.out.println("Create button clicked");
    }

    @FXML
    private void handleRead() {
        System.out.println("Read button clicked");
    }

    @FXML
    private void handleUpdate() {
        System.out.println("Update button clicked");
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete button clicked");
    }
}
