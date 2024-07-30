package ru.sukhobskaya.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sukhobskaya.springcourse.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
