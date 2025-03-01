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
        try {
            Connection connection = DatabaseConnector.getConnection();
            String insertStmt = "INSERT INTO DND_CHARACTER (CHAR_ID, CHAR_NAME, CHAR_RACE, CHAR_CLASS, CHAR_ALIGNMENT, CHAR_BACKGROUND, CHAR_LEVEL, NPC, STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA, IS_IN) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertStmt);
            // character Id
            preparedStatement.setInt(1, character.getId());
            // character name
            preparedStatement.setString(2, character.getName());
            // character race
            preparedStatement.setString(3, character.getRace());
            // character class
            preparedStatement.setString(4, character.getCharacterClass());
            // character alignment
            if (character.getAlignment() == null) {
                preparedStatement.setNull(5, Types.VARCHAR);
            } else {
                preparedStatement.setString(5, character.getAlignment());
            }
            // character background
            if (character.getBackground() == null) {
                preparedStatement.setNull(6, Types.VARCHAR);
            } else {
                preparedStatement.setString(6, character.getBackground());
            }
            // character level
            preparedStatement.setInt(7, character.getLevel());
            // character isNpc
            preparedStatement.setBoolean(8, character.isNpc());
            // character strength
            preparedStatement.setInt(9, character.getStrength());
            // character dexterity
            preparedStatement.setInt(10, character.getDexterity());
            // character constitution
            preparedStatement.setInt(11, character.getConstitution());
            // character intelligence
            preparedStatement.setInt(12, character.getIntelligence());
            // character wisdom
            preparedStatement.setInt(13, character.getWisdom());
            // character charisma
            preparedStatement.setInt(14, character.getCharisma());
            // character location ID
            preparedStatement.setInt(15, character.getLocation());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all characters from the database
     * @return A list of all characters in the database
     */
    public List<DNDCharacter> getAllCharacters() {
        List<DNDCharacter> characters = new ArrayList<>();

        try {
            Connection connection = DatabaseConnector.getConnection();

            Statement stmt = connection.createStatement();
            String queryStmt = "SELECT * FROM DND_CHARACTER";
            ResultSet rs = stmt.executeQuery(queryStmt);

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
        }
        return characters;
    }

    /**
     * Update a character in the database
     * @param characterId The ID of the character to update
     * @param updatedCharacter The updated character object
     */
    public void updateCharacter(int characterId, DNDCharacter updatedCharacter) {
        deleteCharacter(characterId);
        insertCharacter(updatedCharacter);
    }

    /**
     * Update the location of a character in the database
     * @param characterId The ID of the character to update
     * @param locationId The ID of the location to set for the character
     */
    public void updateCharacterLocation(int characterId, int locationId) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            String updateStmt = "UPDATE DND_CHARACTER SET IS_IN = ? WHERE CHAR_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateStmt);
            preparedStatement.setInt(1, locationId);
            preparedStatement.setInt(2, characterId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a character from the database
     * @param characterId The ID of the character to delete
     */
    public void deleteCharacter(int characterId) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            String deleteStmt = "DELETE FROM DND_CHARACTER WHERE CHAR_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteStmt);
            preparedStatement.setInt(1, characterId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a character by their ID
     * @param characterId The ID of the character to retrieve
     * @return The character with the given ID, or null if not found
     */
    public DNDCharacter getCharacterById(int characterId) {
        DNDCharacter newCharacter = null;
        try {
            Connection connection = DatabaseConnector.getConnection();
            String queryStmt = "SELECT * FROM DND_CHARACTER WHERE CHAR_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(queryStmt);
            preparedStatement.setInt(1, characterId);
            ResultSet rs = preparedStatement.executeQuery();

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
        }
        return newCharacter;
    }
}
