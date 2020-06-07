package com.overgrowth.domain.model.entity;

import com.overgrowth.domain.model.request.PersonRequest;
import com.overgrowth.domain.model.response.PersonResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@With
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "persons")
public class Person {
    @Id
    private String id;

    private String name;
    private String email;
    private LocalDate birthDate;
    private Integer weight;
    private Integer height;
    private String imageUrl;

    public static Person of(PersonRequest request) {
        return new Person().withName(request.getName())
                .withEmail(request.getEmail())
                .withBirthDate(request.getBirthDate())
                .withImageUrl(request.getImageUrl());
    }

    public static PersonResponse toResponse(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .birthDate(person.getBirthDate())
                .weight(person.getWeight())
                .age(getAge(person.getBirthDate()))
                .height(person.getHeight())
                .imageUrl(person.getImageUrl())
                .build();
    }

    private static Integer getAge(LocalDate birthDate) {
        if(birthDate != null)
            return Period.between(birthDate, LocalDate.now()).getYears();

        return null;
    }
}
