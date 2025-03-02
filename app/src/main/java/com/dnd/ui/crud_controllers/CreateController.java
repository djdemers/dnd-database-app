package com.dnd.ui.crud_controllers;

import com.dnd.ui.utils.Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import com.dnd.dao.ModelDAO;
import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class CreateController extends Controller {
    
    private Stage createStage;
    private ModelDAO modelDAO;
    

    @FXML
    private VBox fieldsContainer;
    private List<TextField> fields = new ArrayList<>();


    public void setAttributes(List<String> attributes) {
        for (String attr : attributes) {
            TextField field = new TextField();
            field.setPromptText(attr);
            fields.add(field);
            fieldsContainer.getChildren().add(field);
        }
    }



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
