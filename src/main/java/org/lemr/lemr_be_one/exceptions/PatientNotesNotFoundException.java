package org.lemr.lemr_be_one.exceptions;

public class PatientNotesNotFoundException extends RuntimeException {
    public PatientNotesNotFoundException(String message) {
        super(message);
    }
}