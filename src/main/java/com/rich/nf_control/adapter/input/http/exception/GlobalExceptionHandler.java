package com.rich.nf_control.adapter.input.http.exception;

import ch.qos.logback.core.sift.AppenderFactoryUsingSiftModel;
import com.rich.nf_control.core.domain.nota_fiscal.exception.NotaFiscalNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleFieldValidation(MethodArgumentTypeMismatchException ex) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                ex.getMessage(),
                null
        );
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleFieldValidation(MethodArgumentNotValidException ex) {

        List<FieldErrorResponse> fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldErrorResponse(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                )).toList();

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                "Erro de validação nos campos",
                fields
        );

        return ResponseEntity.badRequest().body(apiErrorResponse);
    }

    @ExceptionHandler(NotaFiscalNaoEncontradaException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(NotaFiscalNaoEncontradaException exception) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "NOTA_FISCAL_NAO_ENCONTRADA",
                exception.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }
}