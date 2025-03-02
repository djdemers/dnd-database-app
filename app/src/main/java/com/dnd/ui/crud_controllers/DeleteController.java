package com.dnd.ui.crud_controllers;

import com.dnd.ui.utils.Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import com.dnd.dao.ModelDAO;
import com.dnd.model.Model;
import java.util.List;

public class DeleteController extends Controller {
    
    private Stage deleteStage;
    private ModelDAO modelDAO;
    private List<String> attributes;
    public void setStage(Stage stage) {
        this.deleteStage = stage;
    }   

    public void setModelDAO(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete button clicked");

        // TODO: get the params from the UI and pass them to the modelDAO.delete(model) method
        // modelDAO.delete(model);
    }   

    @FXML
    private void handleCancel() {
        System.out.println("Cancel button clicked");
        deleteStage.close();
    }
    
    
}
