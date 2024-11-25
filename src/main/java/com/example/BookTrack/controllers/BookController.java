package com.example.BookTrack.controllers;

import com.example.BookTrack.models.Book;
import com.example.BookTrack.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Afficher la liste des livres et le formulaire
    @GetMapping
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", new Book()); // Livre vide pour le formulaire
        return "books";
    }

    // Ajouter ou modifier un livre
    @PostMapping
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books"; // Redirection après enregistrement
    }

    // Pré-remplir le formulaire pour la modification
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", bookService.getBookById(id));
        return "books";
    }

    // Supprimer un livre
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
