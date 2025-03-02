package com.dnd.dao;

import com.dnd.model.SessionLog;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dnd.model.Model;

public class SessionLogDAO extends ModelDAO {

    @Override
    public void insert(Model model) {
        // TODO: Implement insert method
    }

    @Override
    public Model get(Model model) {
        // TODO: Implement get method
        return null;
    }

    @Override
    public void update(Model model) {
        // TODO: Implement update method
    }

    @Override
    public void delete(Model model) {
        // TODO: Implement delete method
    }

    // TODO: Get all session logs from the database
    public List<SessionLog> getAllSessionLogs() {
        List<SessionLog> logs = new ArrayList<>();
        // Execute SELECT * FROM SESSION_LOG
        return logs;
    }


    // TODO: Get session log by ID
    public SessionLog getSessionLogById(int logId) {
        // Execute SELECT WHERE SESSLOG_ID = ?
        return null;
    }
}

