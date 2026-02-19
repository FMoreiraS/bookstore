package com.mindblow.bookstore.exception;

public class PublisherNotFoundException extends RuntimeException{
    
    public PublisherNotFoundException(Long id){
        super("Not found publisher with the given id=%d".formatted(id));
    }
}

