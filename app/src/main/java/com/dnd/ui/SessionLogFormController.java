package com.dnd.ui;

import com.dnd.dao.SessionLogDAO;
import com.dnd.model.SessionLog;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.format.DateTimeParseException;

public class SessionLogFormController {

    @FXML
    private TextField sessionIdField;
    @FXML
    private TextField timestampField;
    @FXML
    private TextArea infoTextArea;

    private Stage dialogStage;
    private SessionLog sessionLog;
    private boolean confirmed = false;

    private final SessionLogDAO sessionLogDAO = new SessionLogDAO();


    @FXML
    private void initialize() {
        sessionIdField.setDisable(true);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setSessionLog(SessionLog sessionLog) {
        this.sessionLog = sessionLog;

        if (sessionLog != null) {
            // Display existing data
            sessionIdField.setText(String.valueOf(sessionLog.getSessionId()));
            if (sessionLog.getTimestamp() != null) {
                timestampField.setText(sessionLog.getTimestamp().toString());
            } else {
                timestampField.setText("");
            }
            infoTextArea.setText(sessionLog.getInfoText());
        }
    }

    @FXML
    private void handleSave() {
        if (sessionLog == null) {
            sessionLog = new SessionLog(0, null, "");
        }

        try {
            String tsValue = timestampField.getText().trim();
            if (!tsValue.isEmpty()) {
                sessionLog.setTimestamp(Timestamp.valueOf(tsValue));
            } else {
                sessionLog.setTimestamp(null); 
            }

            sessionLog.setInfoText(infoTextArea.getText());
            confirmed = true;
            dialogStage.close();

        } catch (DateTimeParseException e) {
            showValidationError("Invalid Timestamp Format: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            showValidationError("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        confirmed = false;
        dialogStage.close();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    private void showValidationError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText("Invalid Input");
        alert.showAndWait();
    }
}
