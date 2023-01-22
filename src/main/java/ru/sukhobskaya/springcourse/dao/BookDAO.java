package ru.sukhobskaya.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sukhobskaya.springcourse.models.Book;
import ru.sukhobskaya.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> indexOwner(int person_id) {
        List<Book> books = jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{person_id},
                new BeanPropertyRowMapper<>(Book.class));
        if (books == null)
            books = new ArrayList<>();
        return books;
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void assignPerson(int book_id, int person_id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", person_id, book_id);
    }

    public void releaseBook(int book_id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE id=?", book_id);
    }

    public int bookOwnerId(int id) {
        Person owner = jdbcTemplate.query("SELECT person.id FROM book JOIN person ON book.person_id = person.id WHERE book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        if (owner == null)
            return 0;
        else return owner.getId();
    }
}
