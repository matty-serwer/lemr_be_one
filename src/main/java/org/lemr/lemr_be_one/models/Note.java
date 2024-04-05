package org.lemr.lemr_be_one.models;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Note {
    @Id
    private String id;

    private String patientId;
    private String author;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String type;
    @ElementCollection
    private List<NoteContent> content;

    @PrePersist
    public void onCreate() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

    public Note() {
    }

    public Note(String id, String patientId, String author, ZonedDateTime createdAt, ZonedDateTime updatedAt, String type, List<NoteContent> content) {
        this.id = id;
        this.patientId = patientId;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
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