package com.mindblow.bookstore.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id){
        super("Not found book with the given id=%d".formatted(id));
    }
}

