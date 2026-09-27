package org.backend.smallecommerceapi.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) { super(message); }
}
