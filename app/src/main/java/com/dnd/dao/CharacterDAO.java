package com.dnd.dao;

import com.dnd.model.DNDCharacter;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterDAO {

    // TODO: Insert a new character into the database
    public void insertCharacter(DNDCharacter character) {
        // Use PreparedStatement to insert character into DND_CHARACTER table
        try {
            Connection connection = DatabaseConnector.getConnection();
            String insertStmt = "INSERT INTO DND_CHARACTER" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            // character level
            preparedStatement.setInt(6, character.getLevel());
            // character isNpc
            preparedStatement.setBoolean(7, character.isNpc());
            // character strength
            preparedStatement.setInt(8, character.getStrength());
            // character dexterity
            preparedStatement.setInt(9, character.getDexterity());
            // character constitution
            preparedStatement.setInt(10, character.getConstitution());
            // character intelligence
            preparedStatement.setInt(11, character.getIntelligence());
            // character wisdom
            preparedStatement.setInt(12, character.getWisdom());
            // character charisma
            preparedStatement.setInt(13, character.getCharisma());
            // character location ID
            preparedStatement.setInt(14, character.getLocation());

            preparedStatement.executeUpdate();
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: Retrieve all characters from the database
    public List<DNDCharacter> getAllCharacters() {
        List<DNDCharacter> characters = new ArrayList<>();
        // Execute SELECT * FROM DND_CHARACTER
        return characters;
    }

    // TODO: Update an existing character in the database
    public void updateCharacter(int characterId, DNDCharacter updatedCharacter) {
        // Use PreparedStatement to update character attributes
    }

    // TODO: Delete a character from the database using character ID
    public void deleteCharacter(int characterId) {
        // Use PreparedStatement to delete the character
    }

    // TODO: Find a character by ID
    public DNDCharacter getCharacterById(int characterId) {
        // Execute SELECT WHERE CHAR_ID = ?
        return null;
    }
}
