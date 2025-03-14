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
import com.dnd.model.Notes;
import com.dnd.model.Quest;
import com.dnd.model.SessionLog;
import com.dnd.model.SummaryOfEvents;

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

    // -------------------- NOTES --------------------
    @FXML private TableView<Notes> notesTable;
    @FXML private TableColumn<Notes, Number> noteIdCol;
    @FXML private TableColumn<Notes, Number> noteSessionCol;
    @FXML private TableColumn<Notes, String> noteTextCol;

    // -------------------- SUMMARIES --------------------
    @FXML private TableView<SummaryOfEvents> summaryTable;
    @FXML private TableColumn<SummaryOfEvents, Number> summaryIdCol;
    @FXML private TableColumn<SummaryOfEvents, Number> summarySessionCol;
    @FXML private TableColumn<SummaryOfEvents, String> summaryTextCol;

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
        setupNotesTable();
        setupSummariesTable();
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

    // -------------------- QUEST MANAGEMENT --------------------

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
    private void handleAddQuest() {
        Quest newQuest = new Quest(0, "", "", 0);
        boolean confirmed = showQuestForm(newQuest);
        if (confirmed) {
            questDAO.insertQuest(newQuest);
            loadQuestData();
        }
    }

    @FXML
    private void handleEditQuest() {
        Quest selectedQuest = questTable.getSelectionModel().getSelectedItem();
        if (selectedQuest == null) {
            showAlert("No Quest Selected", "Please select a quest to edit.");
            return;
        }
        boolean confirmed = showQuestForm(selectedQuest);
        if (confirmed) {
            questDAO.updateQuest(selectedQuest);
            loadQuestData();
        }
    }

    @FXML
    private void handleDeleteQuest() {
        Quest selectedQuest = questTable.getSelectionModel().getSelectedItem();
        if (selectedQuest == null) {
            showAlert("No Quest Selected", "Please select a quest to delete.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this quest?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            questDAO.deleteQuest(selectedQuest.getId());
            loadQuestData();
        }
    }

    private boolean showQuestForm(Quest quest) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/QuestForm.fxml"));
            Scene scene = new Scene(loader.load());

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Quest Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            QuestFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setQuest(quest);

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
        sessionLogIdCol.setCellValueFactory(
            cellData -> new javafx.beans.property.SimpleIntegerProperty(
                cellData.getValue().getSessionId()
            )
        );
    
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
        SessionLog newLog = new SessionLog(0, "New Session Log");
        
        boolean confirmed = showSessionLogForm(newLog);
        if (confirmed) {
            sessionLogDAO.insertSessionLog(newLog);
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

    // Show Note Form
    private boolean showNoteForm(Notes note) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/NotesForm.fxml"));
            Scene scene = new Scene(loader.load());
    
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Note Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);
    
            NotesFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setNote(note);
    
            dialogStage.showAndWait();
            return controller.isConfirmed();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Show Summary Form
    private boolean showSummaryForm(SummaryOfEvents summary) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dnd/ui/SummaryForm.fxml"));
            Scene scene = new Scene(loader.load());
    
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Summary Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);
    
            SummaryFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSummary(summary);
    
            dialogStage.showAndWait();
            return controller.isConfirmed();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // -------------- NOTES --------------
    private void setupNotesTable() {
        noteIdCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getNoteId()));
        noteSessionCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getSessionId()));
        noteTextCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNoteText()));

        loadNotesData();
    }

    private void loadNotesData() {
        List<Notes> notesList = sessionLogDAO.getAllNotes();
        notesTable.getItems().setAll(notesList);
    }

    @FXML
    private void handleAddNote() {
        Notes newNote = new Notes(0, 1, "New Note"); 

        boolean confirmed = showNoteForm(newNote);
        if (confirmed) {
            sessionLogDAO.insertNote(newNote);
            loadNotesData();
        }
    }

    @FXML
    private void handleEditNote() {
        Notes selectedNote = notesTable.getSelectionModel().getSelectedItem();
        if (selectedNote == null) {
            showAlert("No Note Selected", "Please select a note to edit.");
            return;
        }

        boolean confirmed = showNoteForm(selectedNote);
        if (confirmed) {
            sessionLogDAO.updateNote(selectedNote);
            loadNotesData();
        }
    }

    @FXML
    private void handleDeleteNote() {
        Notes selectedNote = notesTable.getSelectionModel().getSelectedItem();
        if (selectedNote == null) {
            showAlert("No Note Selected", "Please select a note to delete.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
            "Are you sure you want to delete this note?",
            ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            sessionLogDAO.deleteNote(selectedNote.getNoteId());
            loadNotesData();
        }
    }


    // -------------- SUMMARIES --------------
    private void setupSummariesTable() {
        summaryIdCol.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getSummaryId()));
        summarySessionCol.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getSessionId()));
        summaryTextCol.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSummaryText()));

        loadSummariesData();
    }

    private void loadSummariesData() {
        List<SummaryOfEvents> summaries = sessionLogDAO.getAllSummaries();
        summaryTable.getItems().setAll(summaries);
    }

    @FXML
    private void handleAddSummary() {
        SummaryOfEvents newSummary = new SummaryOfEvents(0, 1, "New Summary");
        boolean confirmed = showSummaryForm(newSummary);
        if (confirmed) {
            sessionLogDAO.insertSummaryOfEvents(newSummary);
            loadSummariesData();
        }
    }

    @FXML
    private void handleEditSummary() {
        SummaryOfEvents selectedSummary = summaryTable.getSelectionModel().getSelectedItem();
        if (selectedSummary == null) {
            showAlert("No Summary Selected", "Please select a summary to edit.");
            return;
        }

        boolean confirmed = showSummaryForm(selectedSummary);
        if (confirmed) {
            sessionLogDAO.updateSummaryOfEvents(selectedSummary);
            loadSummariesData();
        }
    }

    @FXML
    private void handleDeleteSummary() {
        SummaryOfEvents selectedSummary = summaryTable.getSelectionModel().getSelectedItem();
        if (selectedSummary == null) {
            showAlert("No Summary Selected", "Please select a summary to delete.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Are you sure you want to delete this summary?",
            ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            sessionLogDAO.deleteSummaryOfEvents(selectedSummary.getSummaryId());
            loadSummariesData();
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

}



