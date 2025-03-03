package com.dnd.ui;

import com.dnd.app.App;
import com.dnd.dao.CharacterDAO;
import com.dnd.dao.ItemDAO;
import com.dnd.dao.LocationDAO;
import com.dnd.dao.QuestDAO;
import com.dnd.model.DNDCharacter;
import com.dnd.model.Item;
import com.dnd.model.Location;
import com.dnd.model.Quest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainMenuController {
    private App app; // Reference to App for screen switching

    // Character Table
    @FXML private TableView<DNDCharacter> characterTable;
    @FXML private TableColumn<DNDCharacter, Integer> charIdCol;
    @FXML private TableColumn<DNDCharacter, String> charNameCol;
    @FXML private TableColumn<DNDCharacter, String> charRaceCol;
    @FXML private TableColumn<DNDCharacter, String> charClassCol;

    // Item Table
    @FXML private TableView<Item> itemTable;
    @FXML private TableColumn<Item, Number> itemIdCol;
    @FXML private TableColumn<Item, String> itemNameCol;
    @FXML private TableColumn<Item, String> itemTypeCol;
    @FXML private TableColumn<Item, String> itemRarityCol;

    // Location Table
    @FXML private TableView<Location> locationTable;
    @FXML private TableColumn<Location, Number> locIdCol;
    @FXML private TableColumn<Location, String> locNameCol;
    @FXML private TableColumn<Location, String> locTypeCol;

    // Quest Table
    @FXML private TableView<Quest> questTable;
    @FXML private TableColumn<Quest, Number> questIdCol;
    @FXML private TableColumn<Quest, String> questNameCol;
    @FXML private TableColumn<Quest, Number> questExpCol;

    private final CharacterDAO characterDAO = new CharacterDAO();
    private final ItemDAO itemDAO = new ItemDAO();
    private final LocationDAO locationDAO = new LocationDAO();
    private final QuestDAO questDAO = new QuestDAO();

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void initialize() {
        setupCharacterTable();
        setupItemTable();
        setupLocationTable();
        setupQuestTable();
    }

    // 🔹 Setup Characters Table
    private void setupCharacterTable() {
        charIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        charNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        charRaceCol.setCellValueFactory(new PropertyValueFactory<>("race"));
        charClassCol.setCellValueFactory(new PropertyValueFactory<>("characterClass"));
        loadCharacterData();
    }

    // 🔹 Load Character Data
    private void loadCharacterData() {
        List<DNDCharacter> characters = characterDAO.getAllCharacters();
        characterTable.getItems().setAll(characters);
    }

    // 🔹 Handle Adding a New Character
    @FXML
    private void handleAddCharacter() {
        DNDCharacter newCharacter = new DNDCharacter(0, "", "Human", "Warrior", "", "", 1, false, 10, 10, 10, 10, 10, 10, 1);
        boolean confirmed = showCharacterForm(newCharacter);
        if (confirmed) {
            characterDAO.insertCharacter(newCharacter);
            loadCharacterData();
        }
    }

    // 🔹 Handle Editing an Existing Character
    @FXML
    private void handleEditCharacter() {
        DNDCharacter selectedCharacter = characterTable.getSelectionModel().getSelectedItem();
        if (selectedCharacter == null) {
            showAlert("No Character Selected", "Please select a character to edit.");
            return;
        }
        boolean confirmed = showCharacterForm(selectedCharacter);
        if (confirmed) {
            characterDAO.updateCharacter(selectedCharacter);
            loadCharacterData();
        }
    }

    // 🔹 Handle Deleting a Character
    @FXML
    private void handleDeleteCharacter() {
        DNDCharacter selectedCharacter = characterTable.getSelectionModel().getSelectedItem();
        if (selectedCharacter == null) {
            showAlert("No Character Selected", "Please select a character to delete.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this character?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            characterDAO.deleteCharacter(selectedCharacter.getId());
            loadCharacterData();
        }
    }

    // 🔹 Show Character Form (Modal)
    private boolean showCharacterForm(DNDCharacter character) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/CharacterForm.fxml"));
            Scene scene = new Scene(loader.load());

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Character Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            CharacterFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCharacter(character);

            dialogStage.showAndWait();
            return controller.isConfirmed();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Utility: Show Alert Messages
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }




    // 🔹 Setup Items Table
    private void setupItemTable() {
        itemIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        itemNameCol.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemTypeCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        itemRarityCol.setCellValueFactory(cellData -> cellData.getValue().rarityProperty());
        loadItemData();
    }

    // 🔹 Load Item Data
    private void loadItemData() {
        List<Item> items = itemDAO.getAllItems();
        itemTable.getItems().setAll(items);
    }

    private void setupLocationTable() {
        locIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        locNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        locTypeCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        loadLocationData();
    }

    private void loadLocationData() {
        List<Location> locations = locationDAO.getAllLocations();
        locationTable.getItems().setAll(locations);
    }

    private void setupQuestTable() {
        questIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        questNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        questExpCol.setCellValueFactory(cellData -> cellData.getValue().expGainProperty());
        loadQuestData();
    }

    private void loadQuestData() {
        List<Quest> quests = questDAO.getAllQuests();
        questTable.getItems().setAll(quests);
    }

    @FXML
    private void handleLogout() {
        characterTable.getItems().clear();
        itemTable.getItems().clear();
        locationTable.getItems().clear();
        questTable.getItems().clear();
        app.showDatabaseLoginDialog();
    }

}



