package org.backend.smallecommerceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int statusCode;
    private Map<String, String> errors = new HashMap<>();

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
