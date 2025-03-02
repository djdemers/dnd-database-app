package com.dnd.dao;

import com.dnd.model.DNDCharacter;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dnd.model.Model;

public class CharacterDAO extends ModelDAO {

    @Override
    public void insert(Model model) {
        // TODO implement method stub
        System.out.println("Inserting character");
    }

    @Override
    public Model get(Model model) {
        // TODO implement method stub
        System.out.println("Getting character");
        return null;
    }

    @Override
    public void update(Model model) {
        // TODO implement method stub
        System.out.println("Updating character");
    }

    @Override
    public void delete(Model model) {
        // TODO implement method stub
        System.out.println("Deleting character");
    }
    
    // TODO: Retrieve all characters from the database
    public List<DNDCharacter> getAllCharacters() {
        List<DNDCharacter> characters = new ArrayList<>();
        // Execute SELECT * FROM DND_CHARACTER
        return characters;
    }


    // TODO: Find a character by ID
    public DNDCharacter getCharacterById(int characterId) {
        // Execute SELECT WHERE CHAR_ID = ?
        return null;
    }
}
