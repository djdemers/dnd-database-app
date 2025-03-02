package com.dnd.dao;

import com.dnd.model.Model;
import java.util.ArrayList;
import java.util.List;

public class ItemStatsDAO extends ModelDAO {
    private int id;
    private String name;
    private String description;
    private String modelName = "DND_ITEM_STATS";
    private List<String> attributes = new ArrayList<>();

    public ItemStatsDAO() {
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
        System.out.println("Inserting item status");
    }

    @Override
    public Model get(Model model) {
        // TODO implement method stub
        System.out.println("Getting item status");
        return null;
    }

    @Override
    public void update(Model model) {
        // TODO implement method stub
        System.out.println("Updating item status");
    }

    @Override
    public void delete(Model model) {
        // TODO implement method stub
        System.out.println("Deleting item status");
    }
    
}
