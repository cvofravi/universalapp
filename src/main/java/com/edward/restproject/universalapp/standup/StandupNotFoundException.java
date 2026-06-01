package com.edward.restproject.universalapp.standup;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StandupNotFoundException extends RuntimeException {
    public StandupNotFoundException(String message) {
        super(message);
    }
}
