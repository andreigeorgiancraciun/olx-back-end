package com.example.olxpostman.advert.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AdvertNotFoundResponseStatusException extends ResponseStatusException {
    public AdvertNotFoundResponseStatusException() {
        super(HttpStatus.NOT_FOUND, "No advert found with given id");
    }
}
