package com.fastcampus.jpa.FastCampusJPA06.domain.listener;

import com.fastcampus.jpa.FastCampusJPA06.domain.User;
import com.fastcampus.jpa.FastCampusJPA06.domain.UserHistory;
import com.fastcampus.jpa.FastCampusJPA06.repository.UserHistoryRepository;
import com.fastcampus.jpa.FastCampusJPA06.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
@Component
public class UserEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setEmail(user.getEmail());
        userHistory.setName(user.getName());
        userHistory.setUser(user);
        userHistory.setHomeAddress(user.getHomeAddress());
        userHistory.setCompanyAddress(user.getCompanyAddress());
        userHistoryRepository.save(userHistory);
    }


}
