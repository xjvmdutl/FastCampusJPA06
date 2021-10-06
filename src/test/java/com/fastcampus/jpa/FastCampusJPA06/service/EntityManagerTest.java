package com.fastcampus.jpa.FastCampusJPA06.service;

import com.fastcampus.jpa.FastCampusJPA06.domain.User;
import com.fastcampus.jpa.FastCampusJPA06.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void entityManagerTest(){
        //findAll과 같은역활
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }

    @Test
    public void cacheFindTest(){
        //3번의 쿼리가 실행이 된다.
        //만약 Transactional를 걸게 된다면 쿼리는 한번만 실행된다.
        //영속성 컨텍스트내에서 엔티티 내부에서 자동으로 캐시한다.(1차 케시)
        //1차 케시는 Map형태로 만들어 진다.(key - id , value - entity)
        //해당 Key(id) 값을 조회해서 해당 entity가 존재하면 그대로 1차케시에 존재하는 값을 주고,
        //없다면 1차 케시에 등록하고 쿼리를 실행시킨다.
        //findByEmail같은경우 1차케시에서 ID로 저장을 하기 때문에 쿼리가 3번 동작한다.
        //1차 케시를 활용하면 성능적으로 크게 개선 시킬수 있다.(JPA는 ID로 조회가 빈번히 발생하기 때문에_
        //System.out.println(userRepository.findByEmail("martin@fascampus.com"));
        //System.out.println(userRepository.findByEmail("martin@fascampus.com"));
        //System.out.println(userRepository.findByEmail("martin@fascampus.com"));
        //System.out.println(userRepository.findById(2L).get());
        //System.out.println(userRepository.findById(2L).get());
        //System.out.println(userRepository.findById(2L).get());

        userRepository.deleteById(1L);
        //Select문만 존재하고 delete가 발생하지 않는다.
        //영속성 컨텍스트에만 존재하고 실제 DB에는 적용되지 않은것이다

    }

    @Test
    public void cacheFindTest2(){
        //해당 클레스에 @Transactional 을  걸지 않게 되면 각각이 트렌젝션이 된다.

        User user = userRepository.findById(1L).get();
        user.setName("marrrrrrrrtin");

        userRepository.save(user);


        //userRepository.flush();
        //해당 위치에 존재시 update문이 두번 실행된다.
        System.out.println("-----------------");



        user.setEmail("marrrrrrrrtin@fastcampus.com");

        userRepository.save(user);
        /*
        //userRepository.flush();
        //영속성 컨택스트에서 각각의 내용을 가지고 있다가 1번의 업데이트를 통해 한번에 바꿔준다,
        //개발자가 원하는 타임에 영속성을 동작시키고 싶을경우 flush를 사용한다.
        System.out.println(">>> : "+userRepository.findById(1L).get());
        userRepository.flush();
        System.out.println(">>> : "+userRepository.findById(1L).get());
        */
        System.out.println(userRepository.findAll());
    }

}
