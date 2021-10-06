package com.fastcampus.jpa.FastCampusJPA06.service;

import com.fastcampus.jpa.FastCampusJPA06.domain.Book;
import com.fastcampus.jpa.FastCampusJPA06.repository.AuthorRepository;
import com.fastcampus.jpa.FastCampusJPA06.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void transactionTest(){
        try {
            //bookService.putBookAndAuthor();
            bookService.put();
        }catch (RuntimeException e){
            //여러 checked Exception, unchecked Exception이 섞여 발생된다.
            System.out.println(">>> " + e.getMessage());
        }
        System.out.println("books : "+bookRepository.findAll());
        System.out.println("authors : "+authorRepository.findAll());
    }

    @Test
    public void isolationTest(){
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get(1L);
        System.out.println(">>> " + bookRepository.findAll());
    }

    @Test
    public void propagationTest(){
        try {
            bookService.propagationTest();
        }catch (RuntimeException e){
            System.out.println(">>> " + e.getMessage());
        }
        System.out.println("books : "+bookRepository.findAll());
        System.out.println("authors : "+authorRepository.findAll());
    }

    @Test
    public void converterErrorTest(){
        bookService.getAll();
        bookRepository.findAll().forEach(System.out::println);
    }
}