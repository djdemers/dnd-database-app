package com.dnd.dao;

import com.dnd.model.DNDCharacter;
import com.dnd.utils.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterDAO {

    /**
     * Insert a character into the database
     * @param character The character to insert
     */
    public int insertCharacter(DNDCharacter character) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        int newId = -1;

        try {
            conn = DatabaseConnector.getConnection();

            // Check if a character with the same name and race already exists
            String checkStmt = "SELECT CHAR_ID FROM DND_CHARACTER WHERE CHAR_NAME = ? AND CHAR_RACE = ?";
            PreparedStatement checkExistingStmt = conn.prepareStatement(checkStmt);
            checkExistingStmt.setString(1, character.getName());
            checkExistingStmt.setString(2, character.getRace());
            ResultSet rs = checkExistingStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Character already exists with ID: " + rs.getInt(1));
                return rs.getInt(1);
            }

            // Insert new character
            String insertStmt = "INSERT INTO DND_CHARACTER (CHAR_NAME, CHAR_RACE, CHAR_CLASS, CHAR_ALIGNMENT, CHAR_BACKGROUND, CHAR_LEVEL, NPC, STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA, IS_IN) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(insertStmt, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, character.getName());
            stmt.setString(2, character.getRace());
            stmt.setString(3, character.getCharacterClass());
            stmt.setString(4, character.getAlignment());
            stmt.setString(5, character.getBackground());
            stmt.setInt(6, character.getLevel());
            stmt.setBoolean(7, character.isNpc());
            stmt.setInt(8, character.getStrength());
            stmt.setInt(9, character.getDexterity());
            stmt.setInt(10, character.getConstitution());
            stmt.setInt(11, character.getIntelligence());
            stmt.setInt(12, character.getWisdom());
            stmt.setInt(13, character.getCharisma());
            stmt.setInt(14, character.getLocation());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    newId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return newId;
    }



    /**
     *  Get the next available character ID
     */
    private int getNextCharacterId(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        int nextId = 1; // Default ID if the table is empty

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX(CHAR_ID) FROM DND_CHARACTER");

            if (rs.next()) {
                int maxId = rs.getInt(1);
                if (!rs.wasNull()) {
                    nextId = maxId + 1;
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return nextId;
    }


    /**
     * Get all characters from the database
     * @return A list of all characters in the database
     */
    public List<DNDCharacter> getAllCharacters() {
        List<DNDCharacter> characters = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();

            stmt = conn.createStatement();
            String queryStmt = "SELECT * FROM DND_CHARACTER";
            rs = stmt.executeQuery(queryStmt);

            while (rs.next()) {
                DNDCharacter newCharacter = new DNDCharacter(
                        rs.getInt("CHAR_ID"),
                        rs.getString("CHAR_NAME"),
                        rs.getString("CHAR_RACE"),
                        rs.getString("CHAR_CLASS"),
                        rs.getString("CHAR_ALIGNMENT"),
                        rs.getString("CHAR_BACKGROUND"),
                        rs.getInt("CHAR_LEVEL"),
                        rs.getBoolean("NPC"),
                        rs.getInt("STRENGTH"),
                        rs.getInt("DEXTERITY"),
                        rs.getInt("CONSTITUTION"),
                        rs.getInt("INTELLIGENCE"),
                        rs.getInt("WISDOM"),
                        rs.getInt("CHARISMA"),
                        rs.getInt("IS_IN"));
                characters.add(newCharacter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            characters = null;
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
        return characters;
    }

    /**
     * Update a character in the database
     * @param updatedCharacter The updated character object
     */
    public void updateCharacter(DNDCharacter updatedCharacter) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String updateStmt = "UPDATE DND_CHARACTER SET CHAR_NAME = ?, CHAR_RACE = ?, CHAR_CLASS = ?, CHAR_ALIGNMENT = ?, " +
                    "CHAR_BACKGROUND = ?, CHAR_LEVEL = ?, NPC = ?, STRENGTH = ?, DEXTERITY = ?, " +
                    "CONSTITUTION = ?, INTELLIGENCE = ?, WISDOM = ?, CHARISMA = ?, IS_IN = ? WHERE CHAR_ID = ?";
            stmt = conn.prepareStatement(updateStmt);

            stmt.setString(1, updatedCharacter.getName());
            stmt.setString(2, updatedCharacter.getRace());
            stmt.setString(3, updatedCharacter.getCharacterClass());
            stmt.setString(4, updatedCharacter.getAlignment());
            stmt.setString(5, updatedCharacter.getBackground());
            stmt.setInt(6, updatedCharacter.getLevel());
            stmt.setBoolean(7, updatedCharacter.isNpc());
            stmt.setInt(8, updatedCharacter.getStrength());
            stmt.setInt(9, updatedCharacter.getDexterity());
            stmt.setInt(10, updatedCharacter.getConstitution());
            stmt.setInt(11, updatedCharacter.getIntelligence());
            stmt.setInt(12, updatedCharacter.getWisdom());
            stmt.setInt(13, updatedCharacter.getCharisma());
            stmt.setInt(14, updatedCharacter.getLocation());
            stmt.setInt(15, updatedCharacter.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update the location of a character in the database
     * @param characterId The ID of the character to update
     * @param locationId The ID of the location to set for the character
     */
    public void updateCharacterLocation(int characterId, int locationId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String updateStmt = "UPDATE DND_CHARACTER SET IS_IN = ? WHERE CHAR_ID = ?";
            stmt = conn.prepareStatement(updateStmt);
            stmt.setInt(1, locationId);
            stmt.setInt(2, characterId);
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
     * Delete a character from the database
     * @param characterId The ID of the character to delete
     */
    public void deleteCharacter(int characterId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String deleteStmt = "DELETE FROM DND_CHARACTER WHERE CHAR_ID = ?";
            stmt = conn.prepareStatement(deleteStmt);
            stmt.setInt(1, characterId);
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
     * Get a character by their ID
     * @param characterId The ID of the character to retrieve
     * @return The character with the given ID, or null if not found
     */
    public DNDCharacter getCharacterById(int characterId) {
        DNDCharacter newCharacter = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            String queryStmt = "SELECT * FROM DND_CHARACTER WHERE CHAR_ID = ?";
            stmt = conn.prepareStatement(queryStmt);
            stmt.setInt(1, characterId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                newCharacter = new DNDCharacter(
                        rs.getInt("CHAR_ID"),
                        rs.getString("CHAR_NAME"),
                        rs.getString("CHAR_RACE"),
                        rs.getString("CHAR_CLASS"),
                        rs.getString("CHAR_ALIGNMENT"),
                        rs.getString("CHAR_BACKGROUND"),
                        rs.getInt("CHAR_LEVEL"),
                        rs.getBoolean("NPC"),
                        rs.getInt("STRENGTH"),
                        rs.getInt("DEXTERITY"),
                        rs.getInt("CONSTITUTION"),
                        rs.getInt("INTELLIGENCE"),
                        rs.getInt("WISDOM"),
                        rs.getInt("CHARISMA"),
                        rs.getInt("IS_IN"));
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
        return newCharacter;
    }
}
