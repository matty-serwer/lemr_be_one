package org.lemr.lemr_be_one.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

import java.util.UUID;

@Embeddable
public class EmergencyContact {

    private String name;
    private String relationship;
    private String phone;
    private String email;

    // ... constructors
    public EmergencyContact() {
    }

    public EmergencyContact(String name, String relationship, String phone, String email) {
        this.name = name;
        this.relationship = relationship;
        this.phone = phone;
        this.email = email;
    }

    public EmergencyContact(String name, String relationship, String email) {
        this.name = name;
        this.relationship = relationship;
        this.email = email;
    }

    // ... getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}