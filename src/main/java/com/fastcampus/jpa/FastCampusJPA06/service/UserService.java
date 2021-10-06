package com.fastcampus.jpa.FastCampusJPA06.service;

import com.fastcampus.jpa.FastCampusJPA06.domain.User;
import com.fastcampus.jpa.FastCampusJPA06.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put(){
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");

        entityManager.persist(user);
        user.setName("newUserAfterPersist");

        entityManager.merge(user);


        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);


    }

}
