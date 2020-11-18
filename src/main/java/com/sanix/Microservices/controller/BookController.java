package com.sanix.Microservices.controller;

import com.sanix.Microservices.domain.Book;
import com.sanix.Microservices.exceptions.BookAlreadyExistsException;
import com.sanix.Microservices.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    private static final Logger LOGGER=
            LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value="/books", method= RequestMethod.POST, consumes = {"application/json"})
    public Book saveBook(@RequestBody @Valid final Book book){
        LOGGER.debug("Received request to create the {}", book);
        return bookService.saveBook(book);
    }

    @RequestMapping(value="/books", method=RequestMethod.GET, produces={"application/json"})
    public List<Book> listBooks(){
        LOGGER.debug("Received request to list all books");
        return bookService.getList();
    }

    @RequestMapping(value="/books/{id}", method=RequestMethod.GET, produces={"application/json"})
    public Book singleBook(@PathVariable Long id){
        LOGGER.debug("Received request to list a specific book");
        return bookService.getBook(id);
    }

    @RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
    public void deleteBook(@PathVariable Long id){
        LOGGER.debug("Received request to delete a specific book");
        bookService.deleteBook(id);

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsExecption(BookAlreadyExistsException e){
        return e.getMessage();
    }


}
