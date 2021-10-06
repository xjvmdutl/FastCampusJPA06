package com.fastcampus.jpa.FastCampusJPA06.service;

import com.fastcampus.jpa.FastCampusJPA06.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        userService.put();

        userRepository.findAll().forEach(System.out::println);
    }


}