package com.overgrowth.domain.model.request;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Value
public class PersonRequest {

    @NotBlank(message = "name is required")
    String name;

    @Email
    @NotBlank(message = "email is required")
    String email;

    LocalDate birthDate;
    Integer weight;
    Integer height;
    String imageUrl;

}
