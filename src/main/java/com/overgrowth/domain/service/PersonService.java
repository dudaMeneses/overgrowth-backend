package com.overgrowth.domain.service;

import com.overgrowth.domain.model.request.PersonRequest;
import com.overgrowth.domain.model.response.PersonResponse;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<PersonResponse> create(PersonRequest request);
    Mono<PersonResponse> findById(String id);
    Mono<PersonResponse> update(String id, PersonRequest request);
}
