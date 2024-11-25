package com.example.BookTrack.controllers;

import com.example.BookTrack.models.Book;
import com.example.BookTrack.services.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", new Book());
        return "books";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("book", new Book()); // Prépare un objet vide pour le formulaire
        return "form"; // Correspond au fichier form.html
    }

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("books", bookService.getAllBooks()); // Ajoute tous les livres au modèle
        return "list"; // Correspond au fichier list.html
    }

    @PostMapping
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("books", bookService.getAllBooks());
            return "books";
        }
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", bookService.getBookById(id));
        return "books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
