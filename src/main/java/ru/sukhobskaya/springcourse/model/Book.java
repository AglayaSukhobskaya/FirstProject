package ru.sukhobskaya.springcourse.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Data
@Entity
@Table(name = "Book")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotEmpty(message = "Name should not be empty")
    @Size(max = 100, message = "Too long name. It should be less then 100 characters")
    @Column(name = "name")
    String name;

    @NotEmpty(message = "Author name should not be empty")
    @Size(max = 100, message = "Too long author name. It should be less then 100 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Author name should be in this format: \"Firstname Lastname\"")
    @Column(name = "author")
    String author;

    @NotNull(message = "Year should not be empty")
    @Min(value = 1000, message = "Year should not be less than 1000")
    @Max(value = 2024, message = "Year should not be more than 2024")
    @Column(name = "year")
    Integer year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    Person owner;

    @Column(name = "take_book_date")
    LocalDate takeBookDate;

    @Transient
    Boolean isOvertime;

    public Boolean isOvertime() {
        isOvertime = Objects.nonNull(takeBookDate) && ChronoUnit.DAYS.between(takeBookDate, LocalDate.now()) > 10;
        return isOvertime;
    }
}
