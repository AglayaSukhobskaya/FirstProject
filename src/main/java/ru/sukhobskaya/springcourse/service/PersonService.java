package ru.sukhobskaya.springcourse.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.var;
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

    public Person findById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    public void create(Person person) {
        personRepository.saveAndFlush(person);
    }

    public void update(Integer id, @NotNull Person updatedPerson) {
        var personToUpdate = findById(id);
        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setYear(updatedPerson.getYear());
        personRepository.saveAndFlush(personToUpdate);
    }

    public void delete(Integer id) {
        personRepository.deleteById(id);
    }
}
