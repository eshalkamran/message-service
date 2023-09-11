package com.Ravi.messageservice.dto;

public class Notification {
    private Long id;
    private String username;
    private String message;

    // Getters, setters, default constructor, and parameterized constructor
    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public void setUsername(String recipient) {
        username=recipient;
    }

    public void setMessage(String s) {
        message=s;
    }
}
