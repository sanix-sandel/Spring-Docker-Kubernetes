package com.sanix.Microservices.domain;


import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @NotNull
    @Column(name="id", nullable=false, updatable = false)
    private Long id;

    @NotNull
    @Size(max=64)
    @Column(name="author", nullable=false)
    private String author;

    @NotNull
    @Size(max=64)
    @Column(name="title", nullable=false)
    private String title;

    public Book(){};

    public Book(Long id, @Size(max = 64) String author, @Size(max = 64) String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString(){
        return "Book {"+
                "id= "+id+
                ", author='"+author+'\''+
                ", title'"+title+'\''+
                '}';
    }
}
