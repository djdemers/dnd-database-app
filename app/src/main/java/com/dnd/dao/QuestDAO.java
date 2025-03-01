package com.dnd.dao;

import com.dnd.model.Objective;
import com.dnd.model.Quest;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestDAO {

    /**
     * Insert a new quest into the database
     * @param quest Quest object to insert
     */
    public void insertQuest(Quest quest) {
        // Use PreparedStatement to insert into QUEST table
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "INSERT INTO QUEST (Q_ID, Q_NAME, INFO_TEXT, EXP_GAIN) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, quest.getId());
            stmt.setString(2, quest.getName());
            stmt.setString(3, quest.getInfo_text());
            stmt.setInt(4, quest.getExp_gain());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get all quests from the database
     * @return List of Quest objects
     */
    public List<Quest> getAllQuests() {
        // Execute SELECT * FROM QUEST
        List<Quest> quests = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM QUEST");
            while (rs.next()) {
                int id = rs.getInt("Q_ID");
                String name = rs.getString("Q_NAME");
                String info_text = rs.getString("INFO_TEXT");
                int exp_gain = rs.getInt("EXP_GAIN");
                Quest quest = new Quest(id, name, info_text, exp_gain);
                quests.add(quest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return quests;
    }

    /**
     * Update a quest in the database
     * @param quest Quest object to update
     */
    public void updateQuest(Quest quest) {
        // Update quest data
        deleteQuest(quest.getId());
        insertQuest(quest);
    }

    /**
     * Delete a quest from the database
     * @param questId ID of the quest to delete
     */
    public void deleteQuest(int questId) {
        // Delete a quest from the database
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "DELETE FROM QUEST WHERE Q_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, questId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get a quest by its ID
     * @param questId ID of the quest to fetch
     * @return Quest object
     */
    public Quest getQuestById(int questId) {
        // Fetch quest by its ID
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM QUEST WHERE Q_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, questId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Q_NAME");
                String info_text = rs.getString("INFO_TEXT");
                int exp_gain = rs.getInt("EXP_GAIN");
                return new Quest(questId, name, info_text, exp_gain);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Insert a new objective into the database
     * @param obj Objective object to insert
     */
    public void insertObjective(Objective obj) {
        // Use PreparedStatement to insert into OBJECTIVE table
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "INSERT INTO OBJECTIVES (OBJ_ID, QUEST, OBJ_STATUS, INFO_TEXT) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, obj.getId());
            stmt.setInt(2, obj.getQuestId());
            stmt.setString(3, obj.getStatus());
            stmt.setString(4, obj.getInfoText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update an objective in the database
     * @param obj Objective object to update
     */
    public void updateObjective(Objective obj) {
        // Update objective data
        deleteObjective(obj.getId());
        insertObjective(obj);
    }

    /**
     * Delete an objective from the database
     * @param objId ID of the objective to delete
     */
    public void deleteObjective(int objId) {
        // Delete an objective from the database
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "DELETE FROM OBJECTIVES WHERE OBJ_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, objId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get Objectives for a specific quest by the quest ID
     * @param QuestID ID of the quest to fetch objectives for
     * @return List of Objective objects
     */
    public List<Objective> getObjectivesForQuest(int QuestID) {
        // Fetch objectives for a quest
        List<Objective> objectives = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM OBJECTIVES WHERE QUEST = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, QuestID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("OBJ_ID");
                int quest_id = rs.getInt("QUEST");
                String status = rs.getString("OBJ_STATUS");
                String info_text = rs.getString("INFO_TEXT");
                Objective obj = new Objective(id, quest_id, status, info_text);
                objectives.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return objectives;
    }

    /**
     * Get all objectives from the database
     * @return List of Objective objects
     */
    public List<Objective> getAllObjectives() {
        // Fetch all objectives
        List<Objective> objectives = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM OBJECTIVES");
            while (rs.next()) {
                int id = rs.getInt("OBJ_ID");
                int quest_id = rs.getInt("QUEST");
                String status = rs.getString("OBJ_STATUS");
                String info_text = rs.getString("INFO_TEXT");
                Objective obj = new Objective(id, quest_id, status, info_text);
                objectives.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return objectives;
    }

    /**
     * Get an objective by its ID
     * @param Id ID of the objective to fetch
     * @return Objective object
     */
    public Objective getObjectiveById(int Id) {
        // Fetch objective by its ID
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM OBJECTIVES WHERE OBJ_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, Id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int quest_id = rs.getInt("QUEST");
                String status = rs.getString("OBJ_STATUS");
                String info_text = rs.getString("INFO_TEXT");
                return new Objective(Id, quest_id, status, info_text);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
