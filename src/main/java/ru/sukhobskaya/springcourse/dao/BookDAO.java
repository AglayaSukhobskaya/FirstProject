package ru.sukhobskaya.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.sukhobskaya.springcourse.models.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

    private static int BOOKS_COUNT;

    private List<Book> books;

    {
        books = new ArrayList<>();

        books.add(new Book(++BOOKS_COUNT, "Evening in Byzantium", "Irwin Shaw", 1973));
        books.add(new Book(++BOOKS_COUNT, "Martin Eden", "Jack London", 1909));
        books.add(new Book(++BOOKS_COUNT, "An American Tragedy", "Theodore Dreiser", 1925));
        books.add(new Book(++BOOKS_COUNT, "The Grapes of Wrath", "John Steinbeck", 1939));
        books.add(new Book(++BOOKS_COUNT, "Of Human Bondage", "William Somerset Maugham", 1915));
    }

    public List<Book> index() {
        return books;
    }

    public Book show(int id) {
        return books.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }

    public void save(Book book) {
        book.setId(++BOOKS_COUNT);
        books.add(book);
    }

    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = show(id);
        bookToBeUpdated.setName(updatedBook.getName());
        bookToBeUpdated.setAuthor(updatedBook.getAuthor());
        bookToBeUpdated.setYear(updatedBook.getYear());
    }

    public void delete(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}
