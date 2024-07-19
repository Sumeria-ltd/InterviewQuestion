package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/cart")
    public ResponseEntity<String> addBookToCart(@RequestBody Book book) {
        boolean added = bookService.addBookToCart(book);
        if (added) {
            return ResponseEntity.status(201).body("Book added to cart");
        } else {
            return ResponseEntity.status(500).body("Failed to add book to cart");
        }
    }

    @PostMapping("/cart/submit")
    public ResponseEntity<String> submitCart() {
        // Logic for submitting the cart can be added here
        return ResponseEntity.ok("Cart submitted successfully. We will contact you ASAP to arrange the shipment and payment.");
    }
}
