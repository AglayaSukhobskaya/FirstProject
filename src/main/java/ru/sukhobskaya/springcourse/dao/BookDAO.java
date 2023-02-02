package ru.sukhobskaya.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sukhobskaya.springcourse.models.Book;
import ru.sukhobskaya.springcourse.models.Person;

import java.util.List;

@Component
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Session session = sessionFactory.getCurrentSession();
        Book bookToBeUpdated = session.get(Book.class, id);
        bookToBeUpdated.setName(updatedBook.getName());
        bookToBeUpdated.setAuthor(updatedBook.getAuthor());
        bookToBeUpdated.setYear(updatedBook.getYear());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class, id));
    }

    @Transactional
    public void assignOwner(int book_id, int person_id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, book_id);
        Person person = session.get(Person.class, person_id);
        book.setOwner(person);
        person.getBooks().add(book);
    }

    @Transactional
    public void releaseBook(int book_id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, book_id);
        book.setOwner(null);
    }

}
