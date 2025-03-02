package com.dnd.model;

import java.sql.Timestamp;

public class SessionLog {
    private int sessionId;
    private Timestamp timestamp;
    private String info_text;

    public SessionLog(int sessionId, String info_text) {
        this.sessionId = sessionId;
        this.info_text = info_text;
    }

    public SessionLog(int sessionId, Timestamp timestamp, String info_text) {
        this.sessionId = sessionId;
        this.timestamp = timestamp;
        this.info_text = info_text;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getInfoText() {
        return info_text;
    }

    public void setInfoText(String info_text) {
        this.info_text = info_text;
    }

    @Override
    public String toString() {
        return String.format("Session ID: %d, Timestamp: %s, Info Text: %s", sessionId, timestamp, info_text);
    }

}
