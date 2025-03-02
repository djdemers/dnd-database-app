package com.dnd.dao;

import com.dnd.model.Model;
import java.util.ArrayList;
import java.util.List;
import com.dnd.model.Objective;


public class ObjectivesDAO extends ModelDAO {
    
    @Override
    public void insert(Model model) {
        // TODO: Implement insert method
    }

    @Override
    public void update(Model model) {
        // TODO: Implement update method
    }

    @Override
    public void delete(Model model) {
        // TODO: Implement delete method
    }

    @Override
    public Model get(Model model) {
        // TODO: Implement get method
        return null;
    }

    // TODO: Get all items from the database
    public List<Objective> getAllObjectives() {
        List<Objective> objectives = new ArrayList<>();
        // Execute SELECT * FROM OBJECTIVES
        return objectives;
    }

    // TODO: Retrieve an item by ID
    public Objective getObjectiveById(int objectiveId) {
        // Execute SELECT WHERE OBJECTIVES_ID = ?
        return null;
    }
    
}
