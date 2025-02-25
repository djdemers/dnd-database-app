package com.dnd.dao;

import com.dnd.model.SessionLog;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionLogDAO {

    // TODO: Insert a new session log into the database
    public void insertSessionLog(SessionLog log) {
        // Use PreparedStatement to insert session log
    }

    // TODO: Get all session logs from the database
    public List<SessionLog> getAllSessionLogs() {
        List<SessionLog> logs = new ArrayList<>();
        // Execute SELECT * FROM SESSION_LOG
        return logs;
    }

    // TODO: Delete a session log entry by ID
    public void deleteSessionLog(int logId) {
        // Delete session log using its ID
    }

    // TODO: Get session log by ID
    public SessionLog getSessionLogById(int logId) {
        // Execute SELECT WHERE SESSLOG_ID = ?
        return null;
    }
}

