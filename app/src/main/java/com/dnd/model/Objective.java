package com.dnd.model;

public class Objective {
    // TODO: Define objective attributes
    private int id;
    private int quest_id;
    private String status;
    private String info_text;

    // TODO: Implement constructor
    public Objective(int id, int quest_id, String status, String info_text) {
        this.id = id;
        this.quest_id = quest_id;
        this.status = status;
        this.info_text = info_text;
    }


    // TODO: Generate Getters and Setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestId() {
        return quest_id;
    }

    public void setQuestId(int quest_id) {
        this.quest_id = quest_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfoText() {
        return info_text;
    }

    public void setInfoText(String info_text) {
        this.info_text = info_text;
    }

    // TODO: Override toString() for easy output
    @Override
    public String toString() {
        return String.format("ID: %d, Quest ID: %d, Status: %s, Info: %s", id, quest_id, status, info_text);
    }

}
