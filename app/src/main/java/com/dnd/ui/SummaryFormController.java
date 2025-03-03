package com.dnd.ui;

import com.dnd.model.SummaryOfEvents;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SummaryFormController {

    @FXML
    private TextField summaryIdField;
    @FXML
    private TextField sessionIdField;
    @FXML
    private TextArea summaryTextArea;

    private Stage dialogStage;
    private SummaryOfEvents summary;
    private boolean confirmed = false;

    @FXML
    private void initialize() {
        summaryIdField.setDisable(true);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setSummary(SummaryOfEvents summary) {
        this.summary = summary;
        if (summary != null) {
            summaryIdField.setText(String.valueOf(summary.getSummaryId()));
            sessionIdField.setText(String.valueOf(summary.getSessionId()));
            summaryTextArea.setText(summary.getSummaryText());
        }
    }

    @FXML
    private void handleSave() {
        if (summary == null) {
            summary = new SummaryOfEvents(0, 0, "");
        }
        summary.setSummaryText(summaryTextArea.getText());
        try {
            summary.setSessionId(Integer.parseInt(sessionIdField.getText()));
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
