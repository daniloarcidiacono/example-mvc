package com.objectway.stage.examples.mvc.advice;

import com.objectway.stage.examples.mvc.dto.ErrorDTO;
import com.objectway.stage.examples.mvc.dto.ValidationErrorDTO;
import com.objectway.stage.examples.mvc.exception.UnitMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MvcControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleException(final Exception ex) {
        return new ErrorDTO(ex.getMessage(), ex.getCause() != null ? ex.getCause().getMessage() : null);
    }

    @ExceptionHandler(UnitMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleUnitMismatchException(final UnitMismatchException ex) {
        return new ErrorDTO(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO handleMethodArgumentNotValid(final MethodArgumentNotValidException exception) {
        return new ValidationErrorDTO("Validation error", exception.getBindingResult());
    }
}
