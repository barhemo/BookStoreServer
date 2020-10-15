package com.booksystemserver.booksystemserver.service;

import com.booksystemserver.booksystemserver.model.Book;
import com.booksystemserver.booksystemserver.model.BookWithQuantity;
import com.booksystemserver.booksystemserver.model.BookWithShippingAddress;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final HashMap<Book, Integer> books;
    private final HashMap<Book, String> shippings;

    public BookService() {
        books = new HashMap<>();
        shippings = new HashMap<>();
    }

    public void addNewBook(BookWithQuantity bookWithQuantity) throws Exception {
        if (bookWithQuantity.getQuantity() == 0) {
            throw new Exception("Can't add 0 books");
        }
        Optional<Book> key = books.keySet().stream().filter(book1 -> book1.equals(bookWithQuantity.getBook())).findFirst();
        if (key.isPresent()) {
            synchronized (key.get()) {
                books.merge(bookWithQuantity.getBook(), bookWithQuantity.getQuantity(), Integer::sum);
            }
        } else {
            synchronized (books) {
                books.merge(bookWithQuantity.getBook(), bookWithQuantity.getQuantity(), Integer::sum);
            }
        }
    }

    public List<BookWithQuantity> getAllBooks() {
        return books.keySet().stream().map(book -> new BookWithQuantity(book, books.get(book))).collect(Collectors.toList());
    }

    public void buyBook(BookWithShippingAddress bookWithShippingAddress) throws Exception {
        Optional<Book> key = books.keySet().stream().filter(book1 -> book1.equals(bookWithShippingAddress.getBook())).findFirst();
        if (key.isPresent()) {
            synchronized (key.get()) {
                Integer quantity = books.get(bookWithShippingAddress.getBook());
                if (quantity != null) {
                    if (quantity == 1) {
                        books.remove(bookWithShippingAddress.getBook());
                    } else {
                        books.put(bookWithShippingAddress.getBook(), quantity - 1);
                    }
                    shippings.put(bookWithShippingAddress.getBook(), bookWithShippingAddress.getShippingAddress());
                } else {
                    throw new Exception("No books available");
                }
            }
        } else {
            throw new Exception("No books available");
        }
    }
}
