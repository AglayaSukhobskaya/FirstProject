package ru.sukhobskaya.springcourse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Table(name = "Person")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotEmpty(message = "Name should not be empty")
    @Size(max = 100, message = "Too long name. It should be less then 100 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Name should be in this format: \"Firstname Lastname\"")
    @Column(name = "name", nullable = false, unique = true, length = 100)
    String name;

    @NotNull(message = "Year should not be empty")
    @Min(value = 1000, message = "Year should not be less than 1000")
    @Max(value = 2024, message = "Year should not be more than 2024")
    @Column(name = "year", nullable = false)
    Integer year;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    List<Book> books;
}
