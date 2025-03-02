package com.dnd.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Scene;
import com.dnd.dao.ModelDOA;

public class CRUDController {
    @FXML
    private Button createButton;

    @FXML
    private Button readButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private ModelDOA modelDAO;

    private Stage crudStage;

    public void setCRUDStage(Stage stage) {
        this.crudStage = stage;
    }

    public void setModelDAO(ModelDOA modelDAO) {
        this.modelDAO = modelDAO;
    }

    private void renderCreateUI() {   
        System.out.println("Create button clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/Create.fxml"));
            Stage createStage = new Stage();
            createStage.initModality(Modality.WINDOW_MODAL);
            createStage.initOwner(crudStage);
            Scene scene = new Scene(loader.load());

            CreateController controller = loader.getController();
            controller.setCreateStage(createStage);

            createStage.setScene(scene);
            createStage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error loading create stage: " + e.getMessage());
        }
    }

    private void renderReadUI() {
        System.out.println("Read button clicked");
    }

    private void renderUpdateUI() {
        System.out.println("Update button clicked");
    }

    private void renderDeleteUI() {
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
