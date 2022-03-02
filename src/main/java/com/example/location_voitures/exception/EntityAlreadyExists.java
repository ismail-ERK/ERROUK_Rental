package com.example.location_voitures.exception;

public class EntityAlreadyExists extends RuntimeException{
    public EntityAlreadyExists() {
    }

    public EntityAlreadyExists(String message) {
        super(message);
    }
}
