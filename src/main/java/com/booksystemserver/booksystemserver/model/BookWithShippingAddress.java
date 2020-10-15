package com.booksystemserver.booksystemserver.model;

public class BookWithShippingAddress {
    private Book book;
    private String shippingAddress;

    public BookWithShippingAddress(Book book, String shippingAddress) {
        this.book = book;
        this.shippingAddress = shippingAddress;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
