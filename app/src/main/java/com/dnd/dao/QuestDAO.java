package com.dnd.dao;

import com.dnd.model.Quest;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dnd.model.Model;

public class QuestDAO extends ModelDAO {

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

    public List<Quest> getAllQuests() {
        List<Quest> quests = new ArrayList<>();
        // Execute SELECT * FROM QUEST
        return quests;
    }


    // TODO: Get quest details by ID
    public Quest getQuestById(int questId) {
        // Fetch quest by its ID
        return null;
    }
}
