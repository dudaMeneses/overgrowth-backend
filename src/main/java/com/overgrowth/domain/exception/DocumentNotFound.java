package com.overgrowth.domain.exception;

import com.overgrowth.application.data.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DocumentNotFound extends ResponseStatusException{
    public DocumentNotFound(Entity entity, String id) {
        super(HttpStatus.BAD_REQUEST, String.format("%s not found for id '%s'", entity.name(), id));
    }
}
