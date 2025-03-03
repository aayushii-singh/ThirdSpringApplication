package com.example.demo.model;

public class Greeting {
    private String message;

    // Default constructor
    public Greeting() {
    }

    // Parameterized constructor
    public Greeting(String message) {
        this.message = message;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
