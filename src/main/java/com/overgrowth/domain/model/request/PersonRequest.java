package com.overgrowth.domain.model.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Value
@ToString
@EqualsAndHashCode
public class PersonRequest {

    @NotBlank(message = "name is required")
    String name;

    @Email
    @NotBlank(message = "email is required")
    String email;

    @Past(message = "birthDate must be a past date")
    @NotNull(message = "birthDate is required")
    LocalDate birthDate;

    Integer weight;
    Integer height;
    String imageUrl;

}
