package com.overgrowth.infra.service.person;

import com.overgrowth.domain.model.entity.Person;
import com.overgrowth.domain.model.response.PersonResponse;
import com.overgrowth.helper.PersonHelper;
import com.overgrowth.infra.repository.PersonRepository;
import com.overgrowth.infra.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PersonServiceCreateTest {

    @InjectMocks
    private PersonServiceImpl service;

    @Mock
    private PersonRepository repository;

    @Test
    public void whenFindUser_thenReturnUser(){
        var person = new Person().withId("id-test").withName("test name");
        var response = PersonResponse.builder().id("id-test").name("test name").build();

        doReturn(Mono.just(person)).when(repository).findByEmail(anyString());
        doReturn(Mono.empty()).when(repository).save(any(Person.class));

        StepVerifier.create(service.create(PersonHelper.request()))
                .expectNextMatches(response::equals)
                .expectComplete().verify();
    }

    @Test
    public void whenNotFindUser_thenSaveUser(){
        var person = new Person().withId("id-test").withName("test name");
        var response = PersonResponse.builder().id("id-test").name("test name").build();

        doReturn(Mono.empty()).when(repository).findByEmail(anyString());
        doReturn(Mono.just(person)).when(repository).save(any(Person.class));

        StepVerifier.create(service.create(PersonHelper.request()))
                .expectNextMatches(response::equals)
                .expectComplete().verify();
    }

}