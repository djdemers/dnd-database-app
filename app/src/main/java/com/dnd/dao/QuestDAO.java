package com.dnd.dao;

import com.dnd.model.Quest;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestDAO {

    // TODO: Insert a new quest into the database
    public void insertQuest(Quest quest) {
        // Use PreparedStatement to insert into QUEST table
    }

    // TODO: Retrieve all quests from the database
    public List<Quest> getAllQuests() {
        List<Quest> quests = new ArrayList<>();
        // Execute SELECT * FROM QUEST
        return quests;
    }

    // TODO: Update an existing quest
    public void updateQuest(Quest quest) {
        // Update quest data
    }

    // TODO: Delete a quest by ID
    public void deleteQuest(int questId) {
        // Delete a quest from the database
    }

    // TODO: Get quest details by ID
    public Quest getQuestById(int questId) {
        // Fetch quest by its ID
        return null;
    }
}
