package com.dnd.ui.crud_controllers;

import com.dnd.ui.utils.Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import com.dnd.dao.ModelDAO;
import com.dnd.model.Model;
import java.util.List;

public class ReadController extends Controller {
    
    private Stage readStage;
    private ModelDAO modelDAO;
    private List<String> attributes;
    public void setStage(Stage stage) {
        this.readStage = stage;
    }

    public void setModelDAO(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    @FXML
    private void handleRead() {
        System.out.println("Read button clicked");

        // TODO: get the params from the UI and pass them to the modelDAO.read(model) method
        // modelDAO.read(model);
    }   

    @FXML
    private void handleCancel() {
        System.out.println("Cancel button clicked");
        readStage.close();
    }
    
    
    
}
