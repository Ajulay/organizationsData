package ru.bellintegrator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Обработчик исключений
 */
@ControllerAdvice(basePackages = "ru.bellintegrator.controllers")
public class ControlerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CustomException> handleCustomException(Exception e) {
        return new ResponseEntity<>(new CustomException(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    protected final static class CustomException {
        private String error;

        public CustomException(String message) {
            this.error = message;
        }

        public String getError() {
            return error;
        }

        public void setError(String message) {
            this.error = message;
        }
    }

}
