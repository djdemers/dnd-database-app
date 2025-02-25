package com.dnd.model;

public class DNDCharacter {
    // TODO: Define character attributes
    private int id;
    private String name;
    private String race;
    private String characterClass;
    private String alignment;
    private int level;

    // TODO: Implement constructor
    public DNDCharacter(int id, String name, String race, String characterClass, String alignment, int level) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.alignment = alignment;
        this.level = level;
    }

    // TODO: Generate Getters and Setters for all fields

    // TODO: Override toString() for easy display
    @Override
    public String toString() {
        return String.format("%s the %s %s (Level %d)", name, race, characterClass, level);
    }
}