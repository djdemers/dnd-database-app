package com.dnd.dao;

import com.dnd.model.Notes;
import com.dnd.model.SessionLog;
import com.dnd.model.SummaryOfEvents;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionLogDAO {

    //////////////////////////////////////////////////////////////////
    /// Session Log
    //////////////////////////////////////////////////////////////////

    public void insertSessionLog(SessionLog log) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
    
        try {
            conn = DatabaseConnector.getConnection();
    
            // Insert only INFO_TEXT (and optionally TIME_STAMP) so ID auto-increments
            String query = "INSERT INTO SESSION_LOG (INFO_TEXT) VALUES (?)";
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, log.getInfoText());
            stmt.executeUpdate();
    
            // If you want to retrieve the newly generated ID:
            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1); // or "SESSLOG_ID"
                log.setSessionId(newId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, generatedKeys);
        }
    }
    

    public List<SessionLog> getAllSessionLogs() {
        List<SessionLog> logs = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM SESSION_LOG");

            while (rs.next()) {
                int id = rs.getInt("SESSLOG_ID");
                Timestamp timestamp = rs.getTimestamp("TIME_STAMP");
                String infoText = rs.getString("INFO_TEXT");

                SessionLog log = new SessionLog(id, timestamp, infoText);
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return logs;
    }

    public void deleteSessionLog(int logId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "DELETE FROM SESSION_LOG WHERE SESSLOG_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, logId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public SessionLog getSessionLogById(int logId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM SESSION_LOG WHERE SESSLOG_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, logId);

            rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("TIME_STAMP");
                String infoText = rs.getString("INFO_TEXT");

                return new SessionLog(logId, timestamp, infoText);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    public void updateSessionLog(SessionLog log) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "UPDATE SESSION_LOG SET TIME_STAMP = ?, INFO_TEXT = ? WHERE SESSLOG_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setTimestamp(1, log.getTimestamp());
            stmt.setString(2, log.getInfoText());
            stmt.setInt(3, log.getSessionId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    //////////////////////////////////////////////////////////////////
    /// Notes
    //////////////////////////////////////////////////////////////////

    public void insertNote(Notes note) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "INSERT INTO NOTES (NOTE_ID, SESSLOG, NOTE) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, note.getNoteId());
            stmt.setInt(2, note.getSessionId());
            stmt.setString(3, note.getNoteText());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void updateNote(Notes note) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "UPDATE NOTES SET SESSLOG = ?, NOTE = ? WHERE NOTE_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, note.getSessionId());
            stmt.setString(2, note.getNoteText());
            stmt.setInt(3, note.getNoteId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void deleteNote(int noteId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "DELETE FROM NOTES WHERE NOTE_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, noteId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public List<Notes> getNotesForSessionLog(int sessionId) {
        List<Notes> notesList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM NOTES WHERE SESSLOG = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, sessionId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int noteId = rs.getInt("NOTE_ID");
                int sesslog = rs.getInt("SESSLOG");
                String noteText = rs.getString("NOTE");

                Notes note = new Notes(noteId, sesslog, noteText);
                notesList.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return notesList;
    }

    public List<Notes> getAllNotes() {
        List<Notes> allNotes = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM NOTES");

            while (rs.next()) {
                int noteId = rs.getInt("NOTE_ID");
                int sesslog = rs.getInt("SESSLOG");
                String noteText = rs.getString("NOTE");

                Notes note = new Notes(noteId, sesslog, noteText);
                allNotes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return allNotes;
    }

    public Notes getNoteById(int noteId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM NOTES WHERE NOTE_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, noteId);

            rs = stmt.executeQuery();
            if (rs.next()) {
                int sesslog = rs.getInt("SESSLOG");
                String noteText = rs.getString("NOTE");
                return new Notes(noteId, sesslog, noteText);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////
    /// Summary Of Events
    //////////////////////////////////////////////////////////////////

    public void insertSummaryOfEvents(SummaryOfEvents summary) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "INSERT INTO SUMMARY_OF_EVENTS (SUMMARY_ID, SESSLOG, SUMMARY) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, summary.getSummaryId());
            stmt.setInt(2, summary.getSessionId());
            stmt.setString(3, summary.getSummaryText());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void updateSummaryOfEvents(SummaryOfEvents summary) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "UPDATE SUMMARY_OF_EVENTS SET SESSLOG = ?, SUMMARY = ? WHERE SUMMARY_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, summary.getSessionId());
            stmt.setString(2, summary.getSummaryText());
            stmt.setInt(3, summary.getSummaryId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void deleteSummaryOfEvents(int summaryId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "DELETE FROM SUMMARY_OF_EVENTS WHERE SUMMARY_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, summaryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public List<SummaryOfEvents> getAllSummaries() {
        List<SummaryOfEvents> allSummaries = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM SUMMARY_OF_EVENTS");

            while (rs.next()) {
                int summaryId = rs.getInt("SUMMARY_ID");
                int sesslog = rs.getInt("SESSLOG");
                String summaryText = rs.getString("SUMMARY");

                SummaryOfEvents summary = new SummaryOfEvents(summaryId, sesslog, summaryText);
                allSummaries.add(summary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return allSummaries;
    }


    public List<SummaryOfEvents> getSummariesForSession(int sessionId) {
        List<SummaryOfEvents> summaries = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM SUMMARY_OF_EVENTS WHERE SESSLOG = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, sessionId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int summaryId = rs.getInt("SUMMARY_ID");
                int sesslog = rs.getInt("SESSLOG");
                String summaryText = rs.getString("SUMMARY");

                SummaryOfEvents summary = new SummaryOfEvents(summaryId, sesslog, summaryText);
                summaries.add(summary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return summaries;
    }




    // Helper function to remove redundancy
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null)  rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

