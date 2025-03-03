package com.dnd.ui;

import com.dnd.app.App;
import com.dnd.dao.CharacterDAO;
import com.dnd.dao.ItemDAO;
import com.dnd.dao.LocationDAO;
import com.dnd.dao.QuestDAO;
import com.dnd.dao.SessionLogDAO;
import com.dnd.model.DNDCharacter;
import com.dnd.model.Item;
import com.dnd.model.Location;
import com.dnd.model.Quest;
import com.dnd.model.SessionLog;
import com.mysql.cj.Session;

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

    // Session Table
    @FXML private TableView<SessionLog> sessionLogTable;
    @FXML private TableColumn<SessionLog, Number> sessionLogIdCol;
    @FXML private TableColumn<SessionLog, String> sessionLogTimeStamp;
    @FXML private TableColumn<SessionLog, String> sessionLogInfoText;

    private final CharacterDAO characterDAO = new CharacterDAO();
    private final ItemDAO itemDAO = new ItemDAO();
    private final LocationDAO locationDAO = new LocationDAO();
    private final QuestDAO questDAO = new QuestDAO();
    private final SessionLogDAO sessionLogDAO = new SessionLogDAO();

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void initialize() {
        setupCharacterTable();
        setupItemTable();
        setupLocationTable();
        setupQuestTable();
        setupSessionLogTable();
    }

    // -------------------- CHARACTER MANAGEMENT --------------------

    private void setupCharacterTable() {
        charIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        charNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        charRaceCol.setCellValueFactory(new PropertyValueFactory<>("race"));
        charClassCol.setCellValueFactory(new PropertyValueFactory<>("characterClass"));
        loadCharacterData();
    }

    private void loadCharacterData() {
        List<DNDCharacter> characters = characterDAO.getAllCharacters();
        characterTable.getItems().setAll(characters);
    }

    @FXML
    private void handleAddCharacter() {
        DNDCharacter newCharacter = new DNDCharacter(0, "", "Human", "Warrior", "", "", 1, false, 10, 10, 10, 10, 10, 10, 1);
        boolean confirmed = showCharacterForm(newCharacter);
        if (confirmed) {
            characterDAO.insertCharacter(newCharacter);
            loadCharacterData();
        }
    }

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

    // -------------------- ITEM MANAGEMENT --------------------

    private void setupItemTable() {
        itemIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        itemNameCol.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemTypeCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        itemRarityCol.setCellValueFactory(cellData -> cellData.getValue().rarityProperty());
        loadItemData();
    }

    private void loadItemData() {
        List<Item> items = itemDAO.getAllItems();
        itemTable.getItems().setAll(items);
    }

    @FXML
    private void handleAddItem() {
        Item newItem = new Item(0, "", "", "Common", "Weapon");  // Creates a new item with default values

        boolean confirmed = showItemForm(newItem);

        if (confirmed) {
            if (newItem.getId() == 0) {
                int newId = itemDAO.insertItem(newItem);
                newItem.setId(newId);
            }
            loadItemData();
        }
    }

    @FXML
    private void handleEditItem() {
        Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert("No Item Selected", "Please select an item to edit.");
            return;
        }
        boolean confirmed = showItemForm(selectedItem);
        if (confirmed) {
            itemDAO.updateItem(selectedItem);
            loadItemData();
        }
    }

    @FXML
    private void handleDeleteItem() {
        Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert("No Item Selected", "Please select an item to delete.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            itemDAO.deleteItem(selectedItem.getId());
            loadItemData();
        }
    }

    private boolean showItemForm(Item item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/ItemForm.fxml"));
            Scene scene = new Scene(loader.load());

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Item Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            ItemFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setItem(item);

            dialogStage.showAndWait();
            return controller.isConfirmed();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // -------------------- LOCATION MANAGEMENT --------------------

    // Setup Locations Table
    private void setupLocationTable() {
        locIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        locNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        locTypeCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        loadLocationData();
    }

    // Load Locations
    private void loadLocationData() {
        List<Location> locations = locationDAO.getAllLocations();
        locationTable.getItems().setAll(locations);
    }

    // Handle Add Location
    @FXML
    private void handleAddLocation() {
        Location newLocation = new Location(0, "", "City", "");
        boolean confirmed = showLocationForm(newLocation);
        if (confirmed) {
            locationDAO.insertLocation(newLocation);
            loadLocationData();
        }
    }

    // Handle Edit Location
    @FXML
    private void handleEditLocation() {
        Location selectedLocation = locationTable.getSelectionModel().getSelectedItem();
        if (selectedLocation == null) {
            showAlert("No Location Selected", "Please select a location to edit.");
            return;
        }
        boolean confirmed = showLocationForm(selectedLocation);
        if (confirmed) {
            locationDAO.updateLocation(selectedLocation);
            loadLocationData();
        }
    }

    // Handle Delete Location
    @FXML
    private void handleDeleteLocation() {
        Location selectedLocation = locationTable.getSelectionModel().getSelectedItem();
        if (selectedLocation == null) {
            showAlert("No Location Selected", "Please select a location to delete.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this location?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            locationDAO.deleteLocation(selectedLocation.getId());
            loadLocationData();
        }
    }

    // Show Location Form
    private boolean showLocationForm(Location location) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/LocationForm.fxml"));
            Scene scene = new Scene(loader.load());

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Location Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            LocationFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLocation(location);

            dialogStage.showAndWait();
            return controller.isConfirmed();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    // -------------------- SESSION LOG MANAGEMENT --------------------

    // Setup SessionLog Table
    private void setupSessionLogTable() {
        // Map the table columns to the SessionLog properties
        sessionLogIdCol.setCellValueFactory(
            cellData -> new javafx.beans.property.SimpleIntegerProperty(
                cellData.getValue().getSessionId()
            )
        );
    
        // Display timestamp as string
        sessionLogTimeStamp.setCellValueFactory(
                cellData -> {
                    if (cellData.getValue().getTimestamp() != null) {
                        return new javafx.beans.property.SimpleStringProperty(
                                cellData.getValue().getTimestamp().toString()
                        );
                    } else {
                        return new javafx.beans.property.SimpleStringProperty("");
                    }
                });
    
        sessionLogInfoText.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getInfoText()
                ));
    
        loadSessionLogData();
    }
    

    // Load SessionLogs
    private void loadSessionLogData() {
        List<SessionLog> logs = sessionLogDAO.getAllSessionLogs();
        sessionLogTable.getItems().setAll(logs);
    }
    

    // Handle Add SessionLog
    @FXML
    private void handleAddSessionLog() {
        // Create a new SessionLog with minimal default data
        // e.g., sessionId = 0 to indicate a new record
        SessionLog newLog = new SessionLog(0, "New Session Log");
        
        // Show the form/dialog if you have one:
        boolean confirmed = showSessionLogForm(newLog);
        if (confirmed) {
            // Insert into DB
            sessionLogDAO.insertSessionLog(newLog);
            // Refresh table
            loadSessionLogData();
        }
    }
    

    // Handle Edit SessionLog
    @FXML
    private void handleEditSessionLog() {
        SessionLog selectedLog = sessionLogTable.getSelectionModel().getSelectedItem();
        if (selectedLog == null) {
            showAlert("No Session Log Selected", "Please select a Session Log to edit.");
            return;
        }

        boolean confirmed = showSessionLogForm(selectedLog);
        if (confirmed) {
            sessionLogDAO.updateSessionLog(selectedLog);
            loadSessionLogData();
        }
    }


    // Handle Delete SessionLog
    @FXML
    private void handleDeleteSessionLog() {
        SessionLog selectedLog = sessionLogTable.getSelectionModel().getSelectedItem();
        if (selectedLog == null) {
            showAlert("No Session Log Selected", "Please select a Session Log to delete.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                "Are you sure you want to delete this Session Log?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            sessionLogDAO.deleteSessionLog(selectedLog.getSessionId());
            loadSessionLogData();
        }
    }


    // Show SessionLog Form
    private boolean showSessionLogForm(SessionLog sessionLog) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/SessionLogForm.fxml"));
            Scene scene = new Scene(loader.load());
    
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Session Log Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);
    
            // Retrieve the controller from the loader
            SessionLogFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSessionLog(sessionLog);
    
            dialogStage.showAndWait();
            return controller.isConfirmed();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    

    // -------------------- UTILITIES --------------------

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleLogout() {
        characterTable.getItems().clear();
        itemTable.getItems().clear();
        locationTable.getItems().clear();
        questTable.getItems().clear();
        app.showDatabaseLoginDialog();
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

}



