package org.lemr.lemr_be_one.exceptions;

public class PatientUpdateException extends RuntimeException {
    public PatientUpdateException(String message) {
        super(message);
    }
}