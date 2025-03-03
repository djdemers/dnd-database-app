package com.dnd.ui;

import com.dnd.model.Notes;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NotesFormController {

    @FXML
    private TextField noteIdField;
    @FXML
    private TextField sessionIdField;
    @FXML
    private TextArea noteTextArea;

    private Stage dialogStage;
    private Notes note;
    private boolean confirmed = false;

    @FXML
    private void initialize() {
        noteIdField.setDisable(true);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setNote(Notes note) {
        this.note = note;
        if (note != null) {
            noteIdField.setText(String.valueOf(note.getNoteId()));
            sessionIdField.setText(String.valueOf(note.getSessionId()));
            noteTextArea.setText(note.getNoteText());
        }
    }

    @FXML
    private void handleSave() {
        if (note == null) {
            note = new Notes(0, 0, "");
        }
        // Collect data from fields
        note.setNoteText(noteTextArea.getText());
        try {
            note.setSessionId(Integer.parseInt(sessionIdField.getText()));
        } catch (NumberFormatException e) {
            showError("Session ID must be a number.");
            return;
        }

        confirmed = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        confirmed = false;
        dialogStage.close();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText("Invalid Input");
        alert.showAndWait();
    }
}
