package com.dnd.dao;

import com.dnd.model.Model;

public class ItemStatsDAO extends ModelDAO {
    private int id;
    private String name;
    private String description;


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
