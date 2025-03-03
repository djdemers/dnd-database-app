package com.dnd.ui;

import com.dnd.dao.QuestDAO;
import com.dnd.model.Objective;
import com.dnd.model.Quest;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class QuestFormController {

    @FXML private TextField questIdField;
    @FXML private TextField questNameField;
    @FXML private TextArea questInfoField;
    @FXML private TextField questExpField;

    // Objective Management
    @FXML private ComboBox<String> objectiveStatusComboBox;
    @FXML private TextArea objectiveInfoField;
    @FXML private TableView<Objective> objectivesTable;
    @FXML private TableColumn<Objective, String> statusColumn;
    @FXML private TableColumn<Objective, String> descriptionColumn;
    @FXML private Button addObjectiveButton;

    private Stage dialogStage;
    private Quest quest;
    private boolean confirmed = false;
    private final QuestDAO questDAO = new QuestDAO();
    private final ObservableList<Objective> objectivesList = FXCollections.observableArrayList();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;

        if (quest != null) {
            questIdField.setText(String.valueOf(quest.getId()));
            questNameField.setText(quest.getName());
            questInfoField.setText(quest.getInfoText());
            questExpField.setText(String.valueOf(quest.getExpGain()));

            // Load objectives for this quest
            objectivesList.setAll(questDAO.getObjectivesForQuest(quest.getId()));
        }
    }

    @FXML
    private void initialize() {
        // Disable quest ID field (auto-generated)
        questIdField.setDisable(true);

        // Set up TableView columns
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInfoText()));

        objectivesTable.setItems(objectivesList);
    }

    @FXML
    private void handleSave() {
        if (quest == null) {
            quest = new Quest(0, "", "", 0);
        }

        // Capture user inputs
        quest.setName(questNameField.getText());
        quest.setInfoText(questInfoField.getText());

        try {
            quest.setExpGain(Integer.parseInt(questExpField.getText()));
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "EXP must be a valid number.");
            return;
        }

        // Check for duplicate quests before inserting
        if (quest.getId() == 0 && questDAO.questExists(quest.getName())) {
            showAlert("Duplicate Quest", "A quest with this name already exists.");
            return;
        }

        if (quest.getId() > 0) {
            questDAO.updateQuest(quest);
        } else {
            int newId = questDAO.insertQuest(quest);
            quest.setId(newId);
        }

        // Save objectives to the database
        for (Objective obj : objectivesList) {
            obj.setQuestId(quest.getId());
            questDAO.insertObjective(obj);
        }

        confirmed = true;
        dialogStage.close();
    }

    @FXML
    private void handleAddObjective() {
        String status = objectiveStatusComboBox.getValue();
        String info = objectiveInfoField.getText();

        if (status != null && !status.isEmpty() && !info.isEmpty()) {
            Objective newObjective = new Objective(0, quest.getId(), status, info);
            objectivesList.add(newObjective);

            // Clear the fields after adding
            objectiveStatusComboBox.getSelectionModel().clearSelection();
            objectiveInfoField.clear();
        } else {
            showAlert("Missing Data", "Please select a status and enter objective info.");
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Quest getQuest() {
        return quest;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
