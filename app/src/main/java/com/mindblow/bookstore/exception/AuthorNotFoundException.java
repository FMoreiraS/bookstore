package com.mindblow.bookstore.exception;

import java.util.Collection;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id){
        super("Not found author with id=%d".formatted(id));
    }
    public AuthorNotFoundException(Collection<Long> ids){
        super("Not found an author within the given ids=%s".formatted(ids));
    }
}
