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

import com.dnd.dao.CharacterDAO;
import com.dnd.dao.ItemDAO;
import com.dnd.dao.ItemStatsDAO;
import com.dnd.dao.LocationDAO;
import com.dnd.dao.QuestDAO;
import com.dnd.dao.SessionLogDAO;
import com.dnd.ui.crud_controllers.CRUDController;
import com.dnd.dao.ModelDAO;
import com.dnd.dao.NotesDAO;
import com.dnd.dao.ObjectivesDAO;


public class DatabaseModelSelectController {

    private Stage modelSelectionStage;
    private boolean confirmed = false;

    public void setModelSelectionStage(Stage stage) {
        this.modelSelectionStage = stage;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void renderCrudUI(ModelDAO modelDAO) throws IOException {
        System.out.println("CRUD button clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/CRUD.fxml"));
            Stage crudStage = new Stage();
            crudStage.setTitle("CRUD");
            crudStage.initModality(Modality.WINDOW_MODAL);
            crudStage.initOwner(modelSelectionStage);
            Scene scene = new Scene(loader.load());

            CRUDController controller = loader.getController();
            controller.setCRUDStage(crudStage);
            controller.setModelDAO(modelDAO);

            crudStage.setScene(scene);
            crudStage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error loading CRUD stage: " + e.getMessage());
        }
    }

    @FXML
    private void handleCharacterButton() throws IOException {
        System.out.println("Character button clicked");
        modelSelectionStage.close();
        CharacterDAO characterDAO = new CharacterDAO();
        renderCrudUI(characterDAO);

    }

    @FXML
    private void handleItemButton() throws IOException  {
        System.out.println("Item button clicked");
        modelSelectionStage.close();
        ItemDAO itemDAO = new ItemDAO();
        renderCrudUI(itemDAO);

    }

    @FXML
    private void handleItemStatsButton() throws IOException {
        System.out.println("Item stats button clicked");
        modelSelectionStage.close();
        ItemStatsDAO itemStatsDAO = new ItemStatsDAO();
        renderCrudUI(itemStatsDAO);

    }

    @FXML
    private void handleLocationButton() throws IOException {
        System.out.println("Location button clicked");
        modelSelectionStage.close();
        LocationDAO locationDAO = new LocationDAO();
        renderCrudUI(locationDAO);

    }   

    @FXML
    private void handleNotesButton() throws IOException {
        System.out.println("Notes button clicked");
        modelSelectionStage.close();
        NotesDAO notesDAO = new NotesDAO();
        renderCrudUI(notesDAO);

    }   

    @FXML
    private void handleObjectivesButton() throws IOException {
        System.out.println("Objectives button clicked");
        modelSelectionStage.close();
        ObjectivesDAO objectivesDAO = new ObjectivesDAO();
        renderCrudUI(objectivesDAO);

    }   

    @FXML
    private void handleQuestButton() throws IOException {
        System.out.println("Quest button clicked");
        modelSelectionStage.close();
        QuestDAO questDAO = new QuestDAO();
        renderCrudUI(questDAO);

    }      

    @FXML
    private void handleSessionLogButton() throws IOException {
        System.out.println("Session log button clicked");
        modelSelectionStage.close();
        SessionLogDAO sessionLogDAO = new SessionLogDAO();
        renderCrudUI(sessionLogDAO);

        }      

    @FXML
    private void handleCancel() {
        System.out.println("Cancel button clicked");    
        modelSelectionStage.close();
    }
    
}
