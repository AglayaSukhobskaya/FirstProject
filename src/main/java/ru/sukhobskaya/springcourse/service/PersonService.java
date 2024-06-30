package ru.sukhobskaya.springcourse.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.sukhobskaya.springcourse.model.Person;
import ru.sukhobskaya.springcourse.repository.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonService {
    PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void update(int id, @NotNull Person updatedPerson) {
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
