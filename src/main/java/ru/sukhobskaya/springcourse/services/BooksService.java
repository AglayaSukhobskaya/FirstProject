package ru.sukhobskaya.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sukhobskaya.springcourse.models.Book;
import ru.sukhobskaya.springcourse.models.Person;
import ru.sukhobskaya.springcourse.repositories.BooksRepository;
import ru.sukhobskaya.springcourse.repositories.PeopleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(Integer page, Integer booksPerPage, boolean sort_by_year) {
        if (page == null && booksPerPage == null) {
            if (sort_by_year)
                return booksRepository.findAll(Sort.by("year"));
            else return booksRepository.findAll();
        } else {
            if (sort_by_year)
                return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
            else return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        }
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assignOwner(int book_id, int person_id) {
        Optional<Book> book = booksRepository.findById(book_id);
        Optional<Person> person = peopleRepository.findById(person_id);
        if (book.isPresent() && person.isPresent()) {
            book.get().setOwner(person.get());
        }
    }

    @Transactional
    public void releaseBook(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            book.get().setOwner(null);
        }
    }

    public List<Book> search(String startingWith) {
        if (startingWith == null)
            return null;
        else
            return booksRepository.findByNameStartingWith(startingWith);
    }
}
