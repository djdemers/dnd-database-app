package com.dnd.dao;

public class ItemStatusDAO {
    private int id;
    private String name;
    private String description;

    public ItemStatusDAO(int id, String name, String description) {
        this.id = id;
        this.name = name;   
        this.description = description;
    }

    public int getId() {
        return id;
    }
}
