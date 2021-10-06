package com.fastcampus.jpa.FastCampusJPA06.repository;

import com.fastcampus.jpa.FastCampusJPA06.domain.Gender;
import com.fastcampus.jpa.FastCampusJPA06.domain.User;
import com.fastcampus.jpa.FastCampusJPA06.domain.UserHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserHistoryRepositoryTest {
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userHistoryTest(){


        User user = new User();
        user.setEmail("martin-new@fastcampus.com");
        user.setName("martin-new");
        userRepository.save(user);
        user.setName("martin-new-new");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void userRelationTest(){
        User user =  new User();
        user.setName("david");
        user.setEmail("david@fastcampus.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        user.setName("daniel");
        userRepository.save(user);

        user.setEmail("daniel@fastcampus.com");
        userRepository.save(user);


        userHistoryRepository.findAll().forEach(System.out::println);
        List<UserHistory> result = userRepository.findByEmail("daniel@fastcampus.com").getUserHistories();
        result.forEach(System.out::println);

        System.out.println("UserHistory.getUser() : "+ userHistoryRepository.findAll().get(0));
    }
}
