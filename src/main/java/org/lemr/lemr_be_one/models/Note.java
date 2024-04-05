package org.lemr.lemr_be_one.models;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Note {
    @Id
    private String id;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }


    private String patientId;
    private String author;
    private ZonedDateTime dateTime;
    private String type;
    @ElementCollection
    private List<NoteContent> content;

    public Note() {
    }

    public Note(String id, String patientId, String author, ZonedDateTime dateTime, String type, List<NoteContent> content) {
        this.id = id;
        this.patientId = patientId;
        this.author = author;
        this.dateTime = dateTime;
        this.type = type;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NoteContent> getContent() {
        return content;
    }

    public void setContent(List<NoteContent> content) {
        this.content = content;
    }
}