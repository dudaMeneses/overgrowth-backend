package com.overgrowth.domain.model.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Value
@ToString
@EqualsAndHashCode
public class PersonRequest {

    @NotBlank(message = "name is required")
    String name;

    @Email(message = "email is invalid")
    @NotBlank(message = "email is required")
    String email;

    @Past(message = "birthDate must be a past date")
    LocalDate birthDate;

    @Positive
    Integer weight;

    @Positive
    Integer height;

    String imageUrl;

}
