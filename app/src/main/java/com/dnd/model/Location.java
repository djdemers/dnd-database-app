package com.dnd.model;

import javafx.beans.property.*;

public class Location {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty type;
    private final StringProperty infoText;

    public Location(int id, String name, String type, String infoText) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.infoText = new SimpleStringProperty(infoText);
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Type: %s", getId(), getName(), getType());
    }
}

