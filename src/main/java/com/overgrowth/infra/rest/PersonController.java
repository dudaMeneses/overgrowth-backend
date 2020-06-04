package com.overgrowth.infra.rest;

import com.overgrowth.domain.model.request.PersonRequest;
import com.overgrowth.domain.model.response.PersonResponse;
import com.overgrowth.domain.service.PersonService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/persons")
public class PersonController {

    @NonNull
    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonResponse> create(@RequestBody @Valid PersonRequest request){
        return personService.create(request);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PersonResponse> findById(@PathVariable String id){
        return personService.findById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PersonResponse> update(@PathVariable String id,
                                       @RequestBody @Valid PersonRequest request){
        return personService.update(id, request);
    }
}
