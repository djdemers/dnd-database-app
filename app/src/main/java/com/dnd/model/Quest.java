package com.dnd.model;

public class Quest {
    private int id;
    private String name;
    private String info_text;
    private int exp_gain;

    public Quest(int id, String name, String info_text, int exp_gain) {
        this.id = id;
        this.name = name;
        this.info_text = info_text;
        this.exp_gain = exp_gain;
    }

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

    public String getInfo_text() {
        return info_text;
    }

    public void setInfo_text(String info_text) {
        this.info_text = info_text;
    }

    public int getExp_gain() {
        return exp_gain;
    }

    public void setExp_gain(int exp_gain) {
        this.exp_gain = exp_gain;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Info: %s, EXP: %d", id, name, info_text, exp_gain);
    }

}
