package com.overgrowth.domain.model.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class PersonResponse {
    private final String id;
    private final String name;
    private final String email;
    private final LocalDate birthDate;
    private final Integer age;
    private final Integer weight;
    private final Integer height;
    private final String imageUrl;
}
