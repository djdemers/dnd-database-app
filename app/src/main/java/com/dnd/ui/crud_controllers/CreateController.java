package com.dnd.ui.crud_controllers;

import com.dnd.ui.utils.Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import com.dnd.dao.ModelDAO;

public class CreateController extends Controller {
    
    private Stage createStage;
    private ModelDAO modelDAO;
    public void setStage(Stage stage) {
        this.createStage = stage;
    }

    public void setModelDAO(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }

    @FXML
    private void handleCreate() {
        System.out.println("Create button clicked");
    }

    @FXML
    private void handleCancel() {
        System.out.println("Cancel button clicked");
        createStage.close();
    }
}
