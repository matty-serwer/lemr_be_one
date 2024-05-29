package org.lemr.lemr_be_one.exceptions;

public class PatientFetchException extends RuntimeException {
    public PatientFetchException(String message) {
        super(message);
    }
}