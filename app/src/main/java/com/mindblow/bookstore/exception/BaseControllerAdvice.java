package com.mindblow.bookstore.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class BaseControllerAdvice{

    @ExceptionHandler(value = AuthorNotFoundException.class)
    public ProblemDetail notFound(AuthorNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(value = BookNotFoundException.class)
    public ProblemDetail notFound(BookNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(value = PublisherNotFoundException.class)
    public ProblemDetail notFound(PublisherNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }
    
    // Handles unique constraint and other errors.
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ProblemDetail integrityError(SQLIntegrityConstraintViolationException ex){
        return ProblemDetail.forStatus(HttpStatus.CONFLICT);
    }

    // Handle unsuported media type.
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ProblemDetail unsuportedMediaType(HttpMediaTypeNotSupportedException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
    }
}

