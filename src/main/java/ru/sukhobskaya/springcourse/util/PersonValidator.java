package ru.sukhobskaya.springcourse.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sukhobskaya.springcourse.model.Person;
import ru.sukhobskaya.springcourse.service.PersonService;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonValidator implements Validator {
    PersonService personService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personService.findById(person.getId()) != null) {
            errors.rejectValue("name", "", "Person with this name already exist");
        }
    }
}
