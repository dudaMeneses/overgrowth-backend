package com.overgrowth.infra.rest.person;

import com.overgrowth.domain.model.entity.Person;
import com.overgrowth.helper.PersonHelper;
import com.overgrowth.infra.repository.PersonRepository;
import com.overgrowth.infra.rest.PersonController;
import com.overgrowth.infra.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PersonController.class)
@Import(PersonServiceImpl.class)
class PersonControllerUpdateTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private PersonRepository repository;

    @Test
    public void whenHappyPath_thenReturnOk(){
        Person person = PersonHelper.create();

        doReturn(Mono.just(person)).when(repository).save(any(Person.class));
        doReturn(Mono.just(person)).when(repository).findById(anyString());

        client.put()
                .uri("/persons/{id}", "id-test")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("name test")
                .jsonPath("$.email").isEqualTo("test@test.com")
                .jsonPath("$.age").isEqualTo(31)
                .jsonPath("$.birthDate").isEqualTo("1988-10-17");
    }

    @Test
    public void whenNotFound_thenReturnNotFound(){
        Person person = PersonHelper.create();

        doReturn(Mono.empty()).when(repository).findById(anyString());

        client.put()
                .uri("/persons/{id}", "id-test")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange().expectStatus().isNotFound();
    }

    @Test
    public void whenNoName_thenReturnBadRequest(){
        Person person = PersonHelper.create().withName(null);

        client.put()
                .uri("/persons/{id}", "id-test")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    public void whenNoEmail_thenReturnBadRequest(){
        Person person = PersonHelper.create().withEmail(null);

        client.put()
                .uri("/persons/{id}", "id-test")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    public void whenBirthDateInTheFuture_thenReturnBadRequest(){
        Person person = PersonHelper.create().withBirthDate(LocalDate.now().plusDays(1));

        client.put()
                .uri("/persons/{id}", "id-test")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    public void whenNoBirthDate_thenReturnBadRequest(){
        Person person = PersonHelper.create().withBirthDate(null);

        client.put()
                .uri("/persons/{id}", "id-test")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .exchange().expectStatus().isBadRequest();
    }

}