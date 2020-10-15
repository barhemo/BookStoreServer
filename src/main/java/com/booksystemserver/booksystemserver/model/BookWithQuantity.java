package com.booksystemserver.booksystemserver.model;

public class BookWithQuantity {
    private Book book;
    private Integer quantity;

    public BookWithQuantity(Book book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
