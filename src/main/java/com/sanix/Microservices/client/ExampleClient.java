package com.sanix.Microservices.client;

import com.sanix.Microservices.domain.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ExampleClient {

    public static void main(String [] args){
        try{
            RestTemplate restTemplate=new RestTemplate();

            ResponseEntity<Book> response=restTemplate.getForEntity("http://localhost:8080/books/1", Book.class);
            System.out.println(response.getBody());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
