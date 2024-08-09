package ru.sukhobskaya.springcourse.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sukhobskaya.springcourse.model.Person;
import ru.sukhobskaya.springcourse.service.PersonService;

import java.util.Objects;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonValidator implements Validator {
    PersonService personService;

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        var person = (Person) target;
        if (Objects.isNull(person.getId())) {
            if (personService.existsByName(person.getName())) {
                var message = String.format("Person with the name %s already exists", person.getName());
                errors.rejectValue("name", "", message);
            }
        } else {
            var foundPerson = personService.findByName(person.getName());
            if (Objects.nonNull(foundPerson) && !Objects.equals(person.getId(), foundPerson.getId())) {
                var message = String.format("Person with the name %s already exists", person.getName());
                errors.rejectValue("name", "", message);
            }
        }
    }
}
