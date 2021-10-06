package com.fastcampus.jpa.FastCampusJPA06.repository;

import com.fastcampus.jpa.FastCampusJPA06.domain.Book;
import com.fastcampus.jpa.FastCampusJPA06.repository.dto.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void bookTest(){
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setAuthorId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());
    }

    @Test
    public void softDelete(){
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));
    }

    @Test
    public void queryTest(){
        bookRepository.findAll().forEach(System.out::println);
        System.out.println("findByCategoryIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtGreaterThanEqual : "
                + bookRepository.findByCategoryIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtGreaterThanEqual(
                        "JPA 초격차 패키지", LocalDateTime.now().minusDays(1L),LocalDateTime.now().minusDays(1L)
        ));
        System.out.println("findByNameRecently : " + bookRepository.findByNameRecently(
                "JPA 초격차 패키지", LocalDateTime.now().minusDays(1L),LocalDateTime.now().minusDays(1L)
        ));

        System.out.println(bookRepository.findBookNameAndCategory());

        bookRepository.findBookNameAndCategory().forEach(b -> {
            System.out.println(b.getName() + " : " + b.getCategory());
        });
        bookRepository.findBookNameAndCategory(PageRequest.of(0,1)).forEach(
            bookNameAndCategory -> { System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory());
        });

        bookRepository.findBookNameAndCategory(PageRequest.of(0,2)).forEach(
            bookNameAndCategory -> { System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory());
        });
    }

    @Test
    public void nativeQueryTest(){
        List<Book> books = bookRepository.findAll();
        for(Book book : books){
            book.setCategory("IT전문서");
        }

        System.out.println("affected rows : " + bookRepository.updateCategories());
        bookRepository.findAll().forEach(System.out::println);


        System.out.println(bookRepository.showTables());
    }

    @Test
    public void converterTest(){
        bookRepository.findAll().forEach(System.out::println);
        Book book = new Book();
        book.setName("또다른 IT전문가 서적");
        book.setStatus(new BookStatus(200));
        bookRepository.save(book);

        System.out.println(bookRepository.findRowRecord().values());
    }


}
