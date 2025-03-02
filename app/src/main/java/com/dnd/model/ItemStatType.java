package com.dnd.model;

public enum ItemStatType {
    STRENGTH("STRENGTH"),
    DEXTERITY("DEXTERITY"),
    CONSTITUTION("CONSTITUTION"),
    INTELLIGENCE("INTELLIGENCE"),
    WISDOM("WISDOM"),
    CHARISMA("CHARISMA");

    private final String name;

    private ItemStatType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
