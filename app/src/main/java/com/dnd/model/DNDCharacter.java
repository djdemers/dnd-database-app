package com.dnd.model;

import javafx.beans.property.*;

public class DNDCharacter {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty race;
    private final StringProperty characterClass;
    private final StringProperty alignment;
    private final StringProperty background;
    private final IntegerProperty level;
    private final BooleanProperty isNpc;
    private final IntegerProperty strength;
    private final IntegerProperty dexterity;
    private final IntegerProperty constitution;
    private final IntegerProperty intelligence;
    private final IntegerProperty wisdom;
    private final IntegerProperty charisma;
    private final IntegerProperty location;

    // Constructor for full object initialization
    public DNDCharacter(int id, String name, String race, String characterClass, String alignment,
                        String background, int level, boolean isNpc, int strength, int dexterity,
                        int constitution, int intelligence, int wisdom, int charisma, int location) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.race = new SimpleStringProperty(race);
        this.characterClass = new SimpleStringProperty(characterClass);
        this.alignment = new SimpleStringProperty(alignment);
        this.background = new SimpleStringProperty(background);
        this.level = new SimpleIntegerProperty(level);
        this.isNpc = new SimpleBooleanProperty(isNpc);
        this.strength = new SimpleIntegerProperty(strength);
        this.dexterity = new SimpleIntegerProperty(dexterity);
        this.constitution = new SimpleIntegerProperty(constitution);
        this.intelligence = new SimpleIntegerProperty(intelligence);
        this.wisdom = new SimpleIntegerProperty(wisdom);
        this.charisma = new SimpleIntegerProperty(charisma);
        this.location = new SimpleIntegerProperty(location);
    }

    // Getters and Setters for JavaFX Properties

    public IntegerProperty idProperty() { return id; }
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }

    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }

    public StringProperty raceProperty() { return race; }
    public String getRace() { return race.get(); }
    public void setRace(String race) { this.race.set(race); }

    public StringProperty characterClassProperty() { return characterClass; }
    public String getCharacterClass() { return characterClass.get(); }
    public void setCharacterClass(String characterClass) { this.characterClass.set(characterClass); }

    public StringProperty alignmentProperty() { return alignment; }
    public String getAlignment() { return alignment.get(); }
    public void setAlignment(String alignment) { this.alignment.set(alignment); }

    public StringProperty backgroundProperty() { return background; }
    public String getBackground() { return background.get(); }
    public void setBackground(String background) { this.background.set(background); }

    public IntegerProperty levelProperty() { return level; }
    public int getLevel() { return level.get(); }
    public void setLevel(int level) { this.level.set(level); }

    public BooleanProperty isNpcProperty() { return isNpc; }
    public boolean isNpc() { return isNpc.get(); }
    public void setNpc(boolean isNpc) { this.isNpc.set(isNpc); }

    public IntegerProperty strengthProperty() { return strength; }
    public int getStrength() { return strength.get(); }
    public void setStrength(int strength) { this.strength.set(strength); }

    public IntegerProperty dexterityProperty() { return dexterity; }
    public int getDexterity() { return dexterity.get(); }
    public void setDexterity(int dexterity) { this.dexterity.set(dexterity); }

    public IntegerProperty constitutionProperty() { return constitution; }
    public int getConstitution() { return constitution.get(); }
    public void setConstitution(int constitution) { this.constitution.set(constitution); }

    public IntegerProperty intelligenceProperty() { return intelligence; }
    public int getIntelligence() { return intelligence.get(); }
    public void setIntelligence(int intelligence) { this.intelligence.set(intelligence); }

    public IntegerProperty wisdomProperty() { return wisdom; }
    public int getWisdom() { return wisdom.get(); }
    public void setWisdom(int wisdom) { this.wisdom.set(wisdom); }

    public IntegerProperty charismaProperty() { return charisma; }
    public int getCharisma() { return charisma.get(); }
    public void setCharisma(int charisma) { this.charisma.set(charisma); }

    public IntegerProperty locationProperty() { return location; }
    public int getLocation() { return location.get(); }
    public void setLocation(int location) { this.location.set(location); }

    @Override
    public String toString() {
        return String.format("%s the %s %s (Level %d) (ID: %d)", name.get(), race.get(), characterClass.get(), level.get(), id.get());
    }
}
