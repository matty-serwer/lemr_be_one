package org.lemr.lemr_be_one.exceptions;

public class ErrorResponse {
    private String message;
    private int code;

    // getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}