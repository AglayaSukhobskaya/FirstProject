package ru.sukhobskaya.springcourse.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sukhobskaya.springcourse.model.Book;
import ru.sukhobskaya.springcourse.repository.BookRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {
    BookRepository bookRepository;
    PersonService personService;

    public List<Book> findAll(Integer page, Integer booksPerPage, Boolean sortByYear) {
        return Objects.isNull(page) && Objects.isNull(booksPerPage) ?
                Objects.nonNull(sortByYear) && sortByYear ?
                        bookRepository.findAll(Sort.by("year")) :
                        bookRepository.findAll() :
                Objects.nonNull(sortByYear) && sortByYear ?
                        bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent() :
                        bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void create(Book book) {
        bookRepository.saveAndFlush(book);
    }

    public void update(Integer id, @NotNull Book updatedBook) {
        var bookToUpdate = findById(id);
        bookToUpdate.setName(updatedBook.getName());
        bookToUpdate.setAuthor(updatedBook.getAuthor());
        bookToUpdate.setYear(updatedBook.getYear());
        bookRepository.saveAndFlush(bookToUpdate);
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    public void takeBook(Integer bookId, Integer personId) {
        var book = findById(bookId);
        var person = personService.findById(personId);
        if (Objects.nonNull(book) && Objects.nonNull(person)) {
            book.setTakeBookDate(LocalDate.now());
            book.setOwner(person);
        }
        bookRepository.saveAndFlush(book);
    }

    public void returnBook(Integer id) {
        var book = findById(id);
        if (Objects.nonNull(book)) {
            book.setTakeBookDate(null);
            book.setOwner(null);
        }
        bookRepository.saveAndFlush(book);
    }

    public Object search(String startingWith) {
        return bookRepository.findByNameStartingWith(startingWith);
    }
}
