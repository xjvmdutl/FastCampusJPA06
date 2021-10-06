package com.fastcampus.jpa.FastCampusJPA06.repository;

import com.fastcampus.jpa.FastCampusJPA06.domain.Book;
import com.fastcampus.jpa.FastCampusJPA06.domain.Publisher;
import com.fastcampus.jpa.FastCampusJPA06.domain.Review;
import com.fastcampus.jpa.FastCampusJPA06.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();
        User user = userRepository.findByEmail("martin@fastcampus.com");
        System.out.println("Review : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(),givenBook(givenPublisher()));
    }

    private User givenUser(){
        return userRepository.findByEmail("martin@fastcampus.com");
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("패스트 캠퍼스");
        return publisherRepository.save(publisher);
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("너무너무 재미있고 즐거운 책이였어요.");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    @Test
    @Transactional
    public void reviewTest(){


        List<Review> reviews = reviewRepository.findAll();

        //리뷰쿼리 한개에 N개의 Comment쿼리가 발생한다.
        //System.out.println(reviews);
        /*
        System.out.println("전체를 가지고 왔습니다.");

        System.out.println(reviews.get(0).getComments());

        System.out.println("첫번째 리뷰의 코멘트들을 가지고 왔습니다.");

        System.out.println(reviews.get(1).getComments());

        System.out.println("두번째 리뷰의 코멘트들을 가지고 왔습니다.");
        */


        //List<Review> reviews = reviewRepository.findAllByFetchJoin();
        //List<Review> reviews = reviewRepository.findAllByEntityGraph();
        reviews.forEach(System.out::println);
    }

}
