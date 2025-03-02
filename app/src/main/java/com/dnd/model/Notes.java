package com.dnd.model;

public class Notes {
    private int noteId;
    private int sessionId;
    private String noteText;
    public Notes(int noteId, int sessionId, String noteText) {
        this.noteId = noteId;
        this.sessionId = sessionId;
        this.noteText = noteText;
    }
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
    @Override
    public String toString() {
        return String.format("Note ID: %d, Session ID: %d, Note Text: %s", noteId, sessionId, noteText);
    }
}
