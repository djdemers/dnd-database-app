package com.dnd.ui;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CreateController {
    
    private Stage createStage;

    public void setCreateStage(Stage stage) {
        this.createStage = stage;
    }

    @FXML
    private void handleCreate() {
        System.out.println("Create button clicked");
    }

    @FXML
    private void handleCancel() {
        System.out.println("Cancel button clicked");
    }
}
