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
    private String name;
    private String email;
    private LocalDate birthDate;
    private Integer age;
    private Integer weight;
    private Integer height;
    private String imageUrl;
}
