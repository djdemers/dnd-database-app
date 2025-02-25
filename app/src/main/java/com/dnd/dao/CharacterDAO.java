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
    }

    // TODO: Retrieve all characters from the database
    public List<DNDCharacter> getAllCharacters() {
        List<DNDCharacter> characters = new ArrayList<>();
        // Execute SELECT * FROM DND_CHARACTER
        return characters;
    }

    // TODO: Update an existing character in the database
    public void updateCharacter(DNDCharacter character) {
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
