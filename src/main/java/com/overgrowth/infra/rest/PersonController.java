package com.overgrowth.infra.rest;

import com.overgrowth.domain.model.request.PersonRequest;
import com.overgrowth.domain.model.response.PersonResponse;
import com.overgrowth.domain.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Api(tags = "persons")
@RequiredArgsConstructor
@RequestMapping(path = "/persons")
public class PersonController {

    @NonNull
    private final PersonService personService;

    @PostMapping
    @ApiOperation("Create an user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Validation error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonResponse> create(@RequestBody @Valid PersonRequest request){
        return personService.create(request);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("Find a specific user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found successfully"),
            @ApiResponse(code = 400, message = "Validation error"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public Mono<PersonResponse> findById(@PathVariable String id){
        return personService.findById(id);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation("Update a specific user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated successfully"),
            @ApiResponse(code = 400, message = "Validation error"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public Mono<PersonResponse> update(@PathVariable String id,
                                       @RequestBody @Valid PersonRequest request){
        return personService.update(id, request);
    }
}
