package com.abdulla.customerinsights.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    public final Instant timestamp = Instant.now();
    public final int status;        // 400, 404, 422, 500...
    public final String error;      // "Bad Request", "Not Found", ...
    public final String message;    // human-readable summary
    public final String path;       // request path
    public final List<FieldError> details; // optional field-level issues

    public static class FieldError {
        public final String field;
        public final String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }

    public ErrorResponse(int status, String error, String message, String path, List<FieldError> details) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.details = details;
    }
}
