package com.overgrowth.domain.model.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PersonResponse {
    private String name;
    private String email;
    private LocalDate birthDate;
    private Integer age;
    private Integer weight;
    private Integer height;
    private String imageUrl;
}
