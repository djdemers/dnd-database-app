package com.dnd.model;

import javafx.beans.property.*;

public class ItemStats {
    private final IntegerProperty itemID;
    private final ObjectProperty<ItemStatType> statType;
    private final IntegerProperty amount;

    public ItemStats(int itemID, ItemStatType statType, int amount) {
        this.itemID = new SimpleIntegerProperty(itemID);
        this.statType = new SimpleObjectProperty<>(statType);
        this.amount = new SimpleIntegerProperty(amount);
    }

    // 🔹 Traditional Getters & Setters
    public int getItemID() {
        return itemID.get();
    }

    public void setItemID(int itemID) {
        this.itemID.set(itemID);
    }

    public ItemStatType getStatType() {
        return statType.get();
    }

    public void setStatType(ItemStatType statType) {
        this.statType.set(statType);
    }

    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    // 🔹 JavaFX Property Methods (For TableView binding)
    public IntegerProperty itemIDProperty() {
        return itemID;
    }

    public ObjectProperty<ItemStatType> statTypeProperty() {
        return statType;
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Item ID: %d, Stat Type: %s, Amount: %d", getItemID(), getStatType(), getAmount());
    }
}



