package com.dnd.ui.crud_controllers;

import com.dnd.ui.utils.Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import com.dnd.dao.ModelDAO;
import java.util.List;

public class UpdateController extends Controller {
    
    private Stage updateStage;
    private ModelDAO modelDAO;
    private List<String> attributes;
    public void setStage(Stage stage) {
        this.updateStage = stage;
    }

    public void setModelDAO(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    @FXML
    private void handleUpdate() {
        System.out.println("Update button clicked");
    }

    @FXML
    private void handleCancel() {
        System.out.println("Cancel button clicked");
        updateStage.close();
    }
}