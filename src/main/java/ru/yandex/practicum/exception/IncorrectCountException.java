package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class IncorrectCountException extends RuntimeException {
    public IncorrectCountException(String message) {
        super(message);
    }
}