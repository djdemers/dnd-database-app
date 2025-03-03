package com.dnd.ui;

import com.dnd.dao.LocationDAO;
import com.dnd.model.Location;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LocationFormController {
    @FXML private TextField locIdField;
    @FXML private TextField locNameField;
    @FXML private ComboBox<String> locTypeBox;
    @FXML private TextArea locInfoTextArea;

    private Stage dialogStage;
    private Location location;
    private boolean confirmed = false;
    private final LocationDAO locationDAO = new LocationDAO();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setLocation(Location location) {
        this.location = location;

        if (location != null) {
            locIdField.setText(String.valueOf(location.getId()));
            locNameField.setText(location.getName());
            locTypeBox.setValue(location.getType());
            locInfoTextArea.setText(location.getInfoText());
        }
    }

    @FXML
    private void initialize() {
        locIdField.setDisable(true); // Auto-generated ID
        locTypeBox.getItems().addAll("City", "Dungeon", "Village", "Forest", "Castle", "Cave");
    }

    @FXML
    private void handleSave() {
        if (location == null) {
            location = new Location(0, "", "", "");
        }

        // Capture user input
        location.setName(locNameField.getText());
        location.setType(locTypeBox.getValue());
        location.setInfoText(locInfoTextArea.getText());

        // If the location has an ID, update it; otherwise, insert a new one
        if (location.getId() > 0) {
            locationDAO.updateLocation(location);
        } else {
            int newId = locationDAO.insertLocation(location);
            location.setId(newId);
        }

        confirmed = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}

