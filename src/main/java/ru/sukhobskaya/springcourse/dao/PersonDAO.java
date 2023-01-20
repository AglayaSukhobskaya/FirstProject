package ru.sukhobskaya.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.sukhobskaya.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 1989));
        people.add(new Person(++PEOPLE_COUNT, "Kate", 1990));
        people.add(new Person(++PEOPLE_COUNT, "Mark", 2000));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setYear(updatedPerson.getYear());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
