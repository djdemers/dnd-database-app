package com.dnd.dao;

import com.dnd.model.Model;
import java.util.ArrayList;
import java.util.List;
import com.dnd.model.Notes;


public class NotesDAO extends ModelDAO {
    private String modelName = "DND_NOTES";
    private List<String> attributes = new ArrayList<>();

    public NotesDAO() {
        attributes.add("NAME");
        attributes.add("DESCRIPTION");
    }

    public List<String> getAttributes() {
        return attributes;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

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

    // TODO: Get all items from the database
    public List<Notes> getAllNotes() {
        List<Notes> notes = new ArrayList<>();
        // Execute SELECT * FROM NOTES
        return notes;
    }

    // TODO: Retrieve an item by ID
    public Notes getNotesById(int notesId) {
        // Execute SELECT WHERE NOTES_ID = ?
        return null;
    }
    
}
