package org.lemr.lemr_be_one.exceptions;

public class NotesNotFoundException extends RuntimeException {
    public NotesNotFoundException(String message) {
        super(message);
    }
}