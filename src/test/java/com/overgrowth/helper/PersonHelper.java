package com.overgrowth.helper;

import com.overgrowth.domain.model.entity.Person;
import com.overgrowth.domain.model.request.PersonRequest;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Month;

@UtilityClass
public class PersonHelper {
    public static PersonRequest request() {
        return new PersonRequest("test", "test@test.com", birthDate(),
                null, null, null);
    }

    public static Person create() {
        return new Person().withId("id-test")
                .withName("name test")
                .withEmail("test@test.com")
                .withBirthDate(birthDate())
                .withWeight(80)
                .withHeight(173);
    }

    private static LocalDate birthDate() {
        return LocalDate.of(1988, Month.OCTOBER, 17);
    }
}
