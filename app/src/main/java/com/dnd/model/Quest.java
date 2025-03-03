package com.dnd.model;

import javafx.beans.property.*;

public class Quest {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty infoText;
    private final IntegerProperty expGain;

    public Quest(int id, String name, String infoText, int expGain) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.infoText = new SimpleStringProperty(infoText);
        this.expGain = new SimpleIntegerProperty(expGain);
    }

    // Getters & Property Methods for JavaFX
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getInfoText() {
        return infoText.get();
    }

    public StringProperty infoTextProperty() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText.set(infoText);
    }

    public int getExpGain() {
        return expGain.get();
    }

    public IntegerProperty expGainProperty() {
        return expGain;
    }

    public void setExpGain(int expGain) {
        this.expGain.set(expGain);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, EXP: %d", getId(), getName(), getExpGain());
    }
}
