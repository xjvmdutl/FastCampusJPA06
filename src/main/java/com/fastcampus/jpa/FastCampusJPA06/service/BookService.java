package com.fastcampus.jpa.FastCampusJPA06.service;

import com.fastcampus.jpa.FastCampusJPA06.domain.Author;
import com.fastcampus.jpa.FastCampusJPA06.domain.Book;
import com.fastcampus.jpa.FastCampusJPA06.repository.AuthorRepository;
import com.fastcampus.jpa.FastCampusJPA06.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;

    private final AuthorService authorService;
    public void put(){
        this.putBookAndAuthor();
    }


    @Transactional
    private void putBookAndAuthor(){
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        throw new RuntimeException("오류가 나서 DB Commit이 발생하지 않습니다.");
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get(Long id){
        System.out.println(">>> "+ bookRepository.findById(id));
        System.out.println(">>> "+ bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> "+ bookRepository.findById(id));
        System.out.println(">>> "+ bookRepository.findAll());
        bookRepository.update();
        entityManager.clear();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void propagationTest(){
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);
        try{
            authorService.putAuthor();
        }catch (RuntimeException e){

        }
        throw new RuntimeException("오류가 발생 하였습니다. transaction 은 어떻게 될까요?");
    }

    @Transactional
    public List<Book> getAll(){
        List<Book> books = bookRepository.findAll();

        books.forEach(System.out::println);
        return books;
    }
}
