package com.rush.exception;

public class NoSuchConfigException extends RuntimeException{
    public NoSuchConfigException(String message) {
        super(message);
    }
}
