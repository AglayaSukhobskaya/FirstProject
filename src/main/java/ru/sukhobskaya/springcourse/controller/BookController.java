package ru.sukhobskaya.springcourse.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sukhobskaya.springcourse.model.Book;
import ru.sukhobskaya.springcourse.model.Person;
import ru.sukhobskaya.springcourse.service.BookService;
import ru.sukhobskaya.springcourse.service.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {
    BookService bookService;
    PersonService personService;

    @GetMapping()
    public String index(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) Boolean sortByYear,
                        Model model) {
        model.addAttribute("books", bookService.findAll(page, booksPerPage, sortByYear));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, @ModelAttribute("person") Person person, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("people", personService.findAll());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBookPage(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookService.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("book", bookService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, @PathVariable("id") Integer id,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/take")
    public String takeBook(@PathVariable("id") Integer id, @ModelAttribute("person") Person person) {
        bookService.takeBook(id, person.getId());
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/return")
    public String returnBook(@PathVariable("id") Integer id) {
        bookService.returnBook(id);
        return "redirect:/books/{id}";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "term", required = false) String startingWith, Model model) {
        model.addAttribute("books", bookService.search(startingWith));
        return "books/search";
    }
}
