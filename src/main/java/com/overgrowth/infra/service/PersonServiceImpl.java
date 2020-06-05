package com.overgrowth.infra.service;

import com.overgrowth.application.data.Entity;
import com.overgrowth.domain.exception.DocumentNotFound;
import com.overgrowth.domain.model.entity.Person;
import com.overgrowth.domain.model.request.PersonRequest;
import com.overgrowth.domain.model.response.PersonResponse;
import com.overgrowth.domain.service.PersonService;
import com.overgrowth.infra.repository.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @NonNull
    private final PersonRepository repository;

    @Override
    public Mono<PersonResponse> create(PersonRequest request) {
        return repository.findByEmail(request.getEmail())
                .switchIfEmpty(repository.save(Person.of(request)))
                .map(Person::toResponse);
    }

    @Override
    public Mono<PersonResponse> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new DocumentNotFound(Entity.PERSON, id)))
                .map(Person::toResponse);
    }

    @Override
    public Mono<PersonResponse> update(String id, PersonRequest request) {
        return repository.findById(id)
                .flatMap(person -> update(request, person))
                .map(Person::toResponse)
                .switchIfEmpty(Mono.error(new DocumentNotFound(Entity.PERSON, request.getEmail())));
    }

    private Mono<Person> update(PersonRequest request, Person person) {
        return repository.save(person.withHeight(request.getHeight())
                        .withWeight(request.getWeight())
                        .withBirthDate(request.getBirthDate()));
    }
}
