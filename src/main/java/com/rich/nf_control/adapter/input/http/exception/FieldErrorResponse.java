package com.rich.nf_control.adapter.input.http.exception;

import lombok.Data;

public record FieldErrorResponse(
        String field,
        String message
) {
}