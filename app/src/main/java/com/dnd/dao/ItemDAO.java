package com.dnd.dao;

import com.dnd.model.Item;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dnd.model.Model;

public class ItemDAO extends ModelDAO {

    private String modelName = "DND_ITEM";

    private List<String> attributes = new ArrayList<>();

    public ItemDAO() {
        attributes.add("NAME");
        attributes.add("DESCRIPTION");
        attributes.add("VALUE");
        attributes.add("WEIGHT");
        attributes.add("QUANTITY");
        attributes.add("LOCATION");
        attributes.add("NOTES");
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
        // TODO implement method stub
        System.out.println("Inserting item");
    }

    @Override
    public Model get(Model model) {
        // TODO implement method stub
        System.out.println("Getting item");
        return null;
    }   

        @Override
    public void update(Model model) {
        // TODO implement method stub
        System.out.println("Updating item");
    }   
    

    @Override
    public void delete(Model model) {
        // TODO implement method stub
        System.out.println("Deleting item");
    }   


    // TODO: Get all items from the database
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        // Execute SELECT * FROM ITEM
        return items;
    }

    // TODO: Retrieve an item by ID
    public Item getItemById(int itemId) {
        // Execute SELECT WHERE ITEM_ID = ?
        return null;
    }

}
