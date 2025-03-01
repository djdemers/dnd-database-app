package com.dnd.model;

public class ItemStats {
    private final int itemID;
    private ItemStatType statType;
    private int amount;

    public ItemStats(int itemID, ItemStatType statType, int amount) {
        this.itemID = itemID;
        this.statType = statType;
        this.amount = amount;
    }

    public int getItemID() {
        return itemID;
    }

    public ItemStatType getStatType() {
        return statType;
    }

    public void setStatType(ItemStatType statType) {
        this.statType = statType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Item ID: %d, Stat Type: %s, Amount: %d", itemID, statType, amount);
    }
}


