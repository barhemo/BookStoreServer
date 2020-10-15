package com.booksystemserver.booksystemserver.controller;

import com.booksystemserver.booksystemserver.model.Book;
import com.booksystemserver.booksystemserver.model.BookWithQuantity;
import com.booksystemserver.booksystemserver.model.BookWithShippingAddress;
import com.booksystemserver.booksystemserver.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping(value = "/buy")
    public ResponseEntity<String> buyBook(@RequestBody BookWithShippingAddress book) {
        try {
            bookService.buyBook(book);
            return ResponseEntity.ok("Book added");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addNewBook(@RequestBody BookWithQuantity bookWithQuantity) {
        try {
            bookService.addNewBook(bookWithQuantity);
            return ResponseEntity.ok("Book added");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<BookWithQuantity>> getAllBooks() {
        try {
            return ResponseEntity.ok(bookService.getAllBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
