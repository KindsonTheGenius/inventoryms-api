package com.kindsonthegenius.inventoryms_springboot_api.exception;

public class UnknownIdentifierException extends RuntimeException {
    public UnknownIdentifierException() {
        super();
    }

    public UnknownIdentifierException(String message) {
        super(message);
    }
    public UnknownIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }

}
