package com.fastcampus.jpa.FastCampusJPA06.service;

import com.fastcampus.jpa.FastCampusJPA06.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void commentTest(){
        commentService.init();
        //commentRepository.findAll().forEach(System.out::println);
        commentService.updateSomething();
        //영향받는 컬럼만 수정하기 위하여 @DynamicUpdate를 활용한다.
        commentService.insertSomething();
    }
}