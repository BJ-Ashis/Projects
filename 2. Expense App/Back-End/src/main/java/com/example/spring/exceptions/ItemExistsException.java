package com.example.spring.exceptions;

public class ItemExistsException extends RuntimeException{

    public ItemExistsException(String message) {
        super(message);
    }
}
