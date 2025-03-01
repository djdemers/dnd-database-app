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
    public void insertCharacter(DNDCharacter character) {
        // Use PreparedStatement to insert character into DND_CHARACTER table
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String insertStmt = "INSERT INTO DND_CHARACTER (CHAR_ID, CHAR_NAME, CHAR_RACE, CHAR_CLASS, CHAR_ALIGNMENT, CHAR_BACKGROUND, CHAR_LEVEL, NPC, STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA, IS_IN) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertStmt);
            // character Id
            stmt.setInt(1, character.getId());
            // character name
            stmt.setString(2, character.getName());
            // character race
            stmt.setString(3, character.getRace());
            // character class
            stmt.setString(4, character.getCharacterClass());
            // character alignment
            if (character.getAlignment() == null) {
                stmt.setNull(5, Types.VARCHAR);
            } else {
                stmt.setString(5, character.getAlignment());
            }
            // character background
            if (character.getBackground() == null) {
                stmt.setNull(6, Types.VARCHAR);
            } else {
                stmt.setString(6, character.getBackground());
            }
            // character level
            stmt.setInt(7, character.getLevel());
            // character isNpc
            stmt.setBoolean(8, character.isNpc());
            // character strength
            stmt.setInt(9, character.getStrength());
            // character dexterity
            stmt.setInt(10, character.getDexterity());
            // character constitution
            stmt.setInt(11, character.getConstitution());
            // character intelligence
            stmt.setInt(12, character.getIntelligence());
            // character wisdom
            stmt.setInt(13, character.getWisdom());
            // character charisma
            stmt.setInt(14, character.getCharisma());
            // character location ID
            stmt.setInt(15, character.getLocation());

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
        deleteCharacter(updatedCharacter.getId());
        insertCharacter(updatedCharacter);
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
