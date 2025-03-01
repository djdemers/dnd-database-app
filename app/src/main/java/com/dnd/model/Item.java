package com.dnd.model;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private int id;
    private String itemName;
    private String infoText;
    private String rarity;
    private String type;

    public Item(int id, String itemName, String infoText, String rarity, String type) {
        this.id = id;
        this.itemName = itemName;
        this.infoText = infoText;
        this.rarity = rarity;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Item ID: %d, Item Name: %s, Type: %s", id, itemName, type);
    }

}
