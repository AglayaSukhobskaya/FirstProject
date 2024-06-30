package ru.sukhobskaya.springcourse.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.var;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sukhobskaya.springcourse.model.Book;
import ru.sukhobskaya.springcourse.repository.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {
    BookRepository bookRepository;
    PersonService personService;

    public List<Book> findAll(Integer page, Integer booksPerPage, boolean sort_by_year) {
        List<Book> bookList;
        if (Objects.isNull(page) && Objects.isNull(booksPerPage)) {
            if (sort_by_year) {
                bookList = bookRepository.findAll(Sort.by("year"));
            } else {
                bookList = bookRepository.findAll();
            }
        } else {
            if (sort_by_year) {
                bookList = bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
            } else {
                bookList = bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
            }
        }
        return bookList;
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void save(Book book) {
        bookRepository.saveAndFlush(book);
    }

    public void update(int id, @NotNull Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.saveAndFlush(updatedBook);
    }

    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public void assignOwner(int bookId, int personId) {
        var book = findById(bookId);
        var person = personService.findById(personId);
        if (Objects.nonNull(book) && Objects.nonNull(person)) {
            book.setAssignOwnerTime(new Date());
            book.setOwner(person);
        }
    }

    public void releaseBook(int id) {
        var book = findById(id);
        if (Objects.nonNull(book)) {
            book.setAssignOwnerTime(null);
            book.setOwner(null);
        }
    }

    public List<Book> search(String startingWith) {
        if (Objects.isNull(startingWith)) {
            return null;
        } else {
            return bookRepository.findByNameStartingWith(startingWith);
        }
    }
}
