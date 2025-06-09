package com.parking.ms_parking.shared.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;



@Slf4j
@ControllerAdvice
public class ApplicationControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody ErrorEntity entityNotFoundException(EntityNotFoundException e) {
        log.error("erreur {}", e.getMessage(), e);
        return new ErrorEntity(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                null, e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ErrorEntity runtimeExceptionHandler(RuntimeException e) {
        log.error("erreur {}", e.getMessage(), e);
        return new ErrorEntity(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                null, e.getMessage());

    }

}
