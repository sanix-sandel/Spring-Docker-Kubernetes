package com.sanix.Microservices.service;

import com.sanix.Microservices.domain.Book;
import com.sanix.Microservices.exceptions.BookAlreadyExistsException;
import com.sanix.Microservices.exceptions.BookNotFoundException;
import com.sanix.Microservices.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Service
@Validated
public class BookServiceImpl implements BookService{

    private static final Logger LOGGER= LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Book saveBook(@NotNull @Valid final Book book){
        LOGGER.debug("Creating {}", book);
        Optional<Book> existing =repository.findById(book.getId());
        if(existing.isPresent()){
            throw new BookAlreadyExistsException(
                    String.format("There already exists a book with id=%s", book.getId()));
        }
        return repository.save(book);
    }

    @Override
    @Transactional
    public List<Book> getList(){
        LOGGER.debug("Retrieving the list od all users");
        return repository.findAll();
    }

    @Override
    @Transactional
    public Book getBook(Long bookId){
        Optional<Book> bookOptional= repository.findById(bookId);
        if(bookOptional.isPresent()){
            throw new BookNotFoundException("Book not found !");
        }
        Book book=bookOptional.get();
        return book;
    }

    @Override
    @Transactional
    public void deleteBook(final Long bookId){
        Optional<Book> bookOptional=repository.findById(bookId);
        Book book=bookOptional.get();
        LOGGER.debug("deleteing {}", book.toString());
        repository.delete(book);
    }
}
