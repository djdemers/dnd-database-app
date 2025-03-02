package com.dnd.model;

public class SummaryOfEvents {
    private int summaryId;
    private int sessionId;
    private String summaryText;

    public SummaryOfEvents(int summaryId, int sessionId, String summaryText) {
        this.summaryId = summaryId;
        this.sessionId = sessionId;
        this.summaryText = summaryText;
    }

    public int getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(int summaryId) {
        this.summaryId = summaryId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSummaryText() {
        return summaryText;
    }

    public void setSummaryText(String summaryText) {
        this.summaryText = summaryText;
    }

    @Override
    public String toString() {
        return String.format("Summary ID: %d, Session ID: %d, Summary Text: %s",
                summaryId, sessionId, summaryText);
    }
}
