package com.example.rickandmorty.model;

public enum Status {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}
