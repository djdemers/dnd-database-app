package com.dnd.model;

public class Location {
    private int ID;
    private String name;
    private String type;
    private String info_text;
    public Location(int ID, String name, String type, String info_text) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.info_text = info_text;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo_text() {
        return info_text;
    }

    public void setInfo_text(String info_text) {
        this.info_text = info_text;
    }
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Type: %s", ID, name, type);
    }

}
