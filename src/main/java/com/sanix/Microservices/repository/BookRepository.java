package com.sanix.Microservices.repository;

import com.sanix.Microservices.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
