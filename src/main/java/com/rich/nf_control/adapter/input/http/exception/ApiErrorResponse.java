package com.rich.nf_control.adapter.input.http.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiErrorResponse(
        LocalDateTime localDateTime,
        int status,
        String error,
        String message,
        List<FieldErrorResponse> fields
) {
}