package ru.sukhobskaya.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sukhobskaya.springcourse.model.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsByName(String name);
    Optional<Person> findByName(String name);
}
