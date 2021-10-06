package com.fastcampus.jpa.FastCampusJPA06.repository;

import com.fastcampus.jpa.FastCampusJPA06.domain.Book;
import com.fastcampus.jpa.FastCampusJPA06.domain.BookReviewInfo;
import com.fastcampus.jpa.FastCampusJPA06.domain.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void crudTest(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);
        bookReviewInfoRepository.save(bookReviewInfo);
        System.out.println(">>>>" + bookReviewInfoRepository.findAll());
    }

    @Test
    public void crudTest2(){
        givenBookReviewInfo();
        Book result = bookReviewInfoRepository
                        .findById(1L)
                        .orElseThrow(RuntimeException::new)
                        .getBook();
        System.out.println(">>> " + result);

        BookReviewInfo result2 = bookRepository
                .findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();
        System.out.println(">>> "+ result2);
    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("Jpa 초격차 패키지");
        book.setAuthorId(1L);
        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);
        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }

    @Transactional
    @Test
    void bookCascadeTest(){
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");
        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publishers : "+publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("슬로우캠퍼스");

        bookRepository.save(book1);

        System.out.println("publishers : "+publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);

        bookRepository.save(book3);


        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());
        System.out.println("book3-publisher : "+ bookRepository.getById(1L).getPublisher());

    }
    @Test
    public void bookRemoveCascadeTest(){
        bookRepository.deleteById(1L);

        System.out.println("books : "+ bookRepository.findAll());
        System.out.println("publishers : "+ publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }

}