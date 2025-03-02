package com.dnd.ui.crud_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Scene;
import com.dnd.dao.ModelDAO;
import com.dnd.ui.utils.WindowSpawner;
public class CRUDController {

    private ModelDAO modelDAO;

    private Stage crudStage;

    public void setCRUDStage(Stage stage) {
        this.crudStage = stage;
    }

    public void setModelDAO(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }

    private void renderCreateUI() {  

        CreateController controller = null;
        WindowSpawner.spawnWindow("/com/dnd/ui/Create.fxml", "Create", crudStage, controller, modelDAO);
        System.out.println("Create button clicked");
    }

    private void renderReadUI() {
        ReadController controller = null;
        WindowSpawner.spawnWindow("/com/dnd/ui/Read.fxml", "Read", crudStage, controller, modelDAO);
        System.out.println("Read button clicked");
    }

    private void renderUpdateUI() {
        UpdateController controller = null;
        WindowSpawner.spawnWindow("/com/dnd/ui/Update.fxml", "Update", crudStage, controller, modelDAO);
        System.out.println("Update button clicked");
    }

    private void renderDeleteUI() {
        DeleteController controller = null;
        WindowSpawner.spawnWindow("/com/dnd/ui/Delete.fxml", "Delete", crudStage, controller, modelDAO);
        System.out.println("Delete button clicked");
    }

    @FXML
    private void handleCreate() {
        System.out.println("Create button clicked");

        crudStage.close();
        renderCreateUI();
    }

    @FXML
    private void handleRead() {
        System.out.println("Read button clicked");
        crudStage.close();
        renderReadUI();
    }

    @FXML
    private void handleUpdate() {
        System.out.println("Update button clicked");
        crudStage.close();
        renderUpdateUI();
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete button clicked");
        crudStage.close();
        renderDeleteUI();
    }
}
