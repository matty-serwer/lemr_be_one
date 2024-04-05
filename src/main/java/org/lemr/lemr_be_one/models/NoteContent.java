package org.lemr.lemr_be_one.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class NoteContent {
    private String text;

    public NoteContent(String s) {
        this.text = s;
    }

    public NoteContent() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
