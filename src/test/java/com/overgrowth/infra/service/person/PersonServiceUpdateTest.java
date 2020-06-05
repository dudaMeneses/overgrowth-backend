package com.overgrowth.infra.service.person;

import com.overgrowth.domain.exception.DocumentNotFound;
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
class PersonServiceUpdateTest {

    @InjectMocks
    private PersonServiceImpl service;

    @Mock
    private PersonRepository repository;

    @Test
    public void whenFindUser_thenUpdate(){
        var person = new Person().withId("id-test").withName("test name");
        var response = PersonResponse.builder().name("test name").build();

        doReturn(Mono.just(person)).when(repository).findById(anyString());
        doReturn(Mono.just(person)).when(repository).save(any(Person.class));

        StepVerifier.create(service.update("id-test", PersonHelper.request()))
                .expectNextMatches(response::equals)
                .expectComplete().verify();
    }

    @Test
    public void whenNotFindUser_thenSaveUser(){
        doReturn(Mono.empty()).when(repository).findById(anyString());

        StepVerifier.create(service.update("id-test", PersonHelper.request()))
                .expectError(DocumentNotFound.class)
                .verify();
    }

}