package com.dnd.model;

public class DNDCharacter {
    // member variables
    private int id;
    private String name;
    private String race;
    private String characterClass;
    private String alignment;
    private int level;
    private boolean isNpc;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private Location location;

    // constructor
    public DNDCharacter(int id, String name, String race, String characterClass, String alignment, int level, boolean isNpc, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, Location location) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.alignment = alignment;
        this.level = level;
        this.isNpc = isNpc;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.location = location;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isNpc() {
        return isNpc;
    }

    public void setNpc(boolean isNpc) {
        this.isNpc = isNpc;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    // TODO: Override toString() for easy display
    @Override
    public String toString() {
        return String.format("%s the %s %s (Level %d)", name, race, characterClass, level);
    }
}