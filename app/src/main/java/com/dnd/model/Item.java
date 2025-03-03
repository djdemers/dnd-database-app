package com.dnd.model;

import javafx.beans.property.*;

public class Item {
    private final IntegerProperty id;
    private final StringProperty itemName;
    private final StringProperty infoText;
    private final StringProperty rarity;
    private final StringProperty type;

    public Item(int id, String itemName, String infoText, String rarity, String type) {
        this.id = new SimpleIntegerProperty(id);
        this.itemName = new SimpleStringProperty(itemName);
        this.infoText = new SimpleStringProperty(infoText);
        this.rarity = new SimpleStringProperty(rarity);
        this.type = new SimpleStringProperty(type);
    }

    // Getters
    public int getId() { return id.get(); }
    public String getItemName() { return itemName.get(); }
    public String getInfoText() { return infoText.get(); }
    public String getRarity() { return rarity.get(); }
    public String getType() { return type.get(); }

    // Property Getters (For JavaFX TableView)
    public IntegerProperty idProperty() { return id; }
    public StringProperty itemNameProperty() { return itemName; }
    public StringProperty infoTextProperty() { return infoText; }
    public StringProperty rarityProperty() { return rarity; }
    public StringProperty typeProperty() { return type; }

    // Setters
    public void setId(int id) { this.id.set(id); }
    public void setItemName(String itemName) { this.itemName.set(itemName); }
    public void setInfoText(String infoText) { this.infoText.set(infoText); }
    public void setRarity(String rarity) { this.rarity.set(rarity); }
    public void setType(String type) { this.type.set(type); }
}
