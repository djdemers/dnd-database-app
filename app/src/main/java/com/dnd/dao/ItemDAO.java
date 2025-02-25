package com.dnd.dao;

import com.dnd.model.Item;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    // TODO: Insert a new item into the ITEM table
    public void insertItem(Item item) {
        // Use PreparedStatement to insert an item
    }

    // TODO: Get all items from the database
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        // Execute SELECT * FROM ITEM
        return items;
    }

    // TODO: Update item details
    public void updateItem(Item item) {
        // Update item properties
    }

    // TODO: Delete an item from the database
    public void deleteItem(int itemId) {
        // Remove item using its ID
    }

    // TODO: Retrieve an item by ID
    public Item getItemById(int itemId) {
        // Execute SELECT WHERE ITEM_ID = ?
        return null;
    }

    // TODO: Manage item stats (INSERT, UPDATE, DELETE)
}
