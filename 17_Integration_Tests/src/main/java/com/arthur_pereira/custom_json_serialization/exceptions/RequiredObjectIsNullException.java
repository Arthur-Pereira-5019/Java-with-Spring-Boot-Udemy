package com.arthur_pereira.custom_json_serialization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {

    public RequiredObjectIsNullException() {
        super("Is not allowed to persist an empty object!");
    }

    public RequiredObjectIsNullException(String message) {
        super(message);
    }
}
