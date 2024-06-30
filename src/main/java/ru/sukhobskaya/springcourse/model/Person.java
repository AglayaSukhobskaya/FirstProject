package ru.sukhobskaya.springcourse.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Your name should be in this format: First name Last name")
    @Column(name = "name")
    private String name;

    @Min(value = 1000, message = "Year should not be less than 1000")
    @Max(value = 2023, message = "Year should not be more than 2023")
    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Book> books;
}
