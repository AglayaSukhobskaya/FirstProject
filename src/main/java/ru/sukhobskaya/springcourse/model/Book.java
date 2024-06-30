package ru.sukhobskaya.springcourse.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 2, max = 30, message = "Author name should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Author name should be in this format: First name Last name")
    @Column(name = "author")
    private String author;

    @Min(value = 900, message = "Year should not be less than 900")
    @Max(value = 2023, message = "Year should not be more than 2023")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "assign_owner_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignOwnerTime;

    @Transient
    private boolean overdue;

    public boolean isOverdue() {
        if (assignOwnerTime == null) {
            overdue = false;
        } else {
            overdue = (new Date().getTime() - assignOwnerTime.getTime()) > 864000000;
        }
        return overdue;
    }

}
