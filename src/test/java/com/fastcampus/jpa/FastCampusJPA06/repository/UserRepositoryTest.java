package com.fastcampus.jpa.FastCampusJPA06.repository;

import com.fastcampus.jpa.FastCampusJPA06.domain.Address;
import com.fastcampus.jpa.FastCampusJPA06.domain.Gender;
import com.fastcampus.jpa.FastCampusJPA06.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private com.fastcampus.jpa.FastCampusJPA06.repository.UserRepository userRepository;

    @Autowired
    private com.fastcampus.jpa.FastCampusJPA06.repository.UserHistoryRepository userHistoryRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void crud(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-update@fastcampus.com");
        userRepository.save(user);
    }

    @Test
    void select(){
        System.out.println(userRepository.findByName("dennis"));
        System.out.println("findByEmail : " + userRepository.findByEmail("martin"));
        System.out.println("getByEmail : " + userRepository.getByEmail("martin"));
        System.out.println("readByEmail : " + userRepository.readByEmail("martin"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("martin"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("martin"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("martin"));
        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("martin"));
        System.out.println("findFirstByName : " + userRepository.findFirst2ByName("martin"));
        System.out.println("findTopByName : " + userRepository.findTop2ByName("martin"));
        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));
        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastCampus.com","martin"));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@fastCampus.com","denis"));
        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreateAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
        System.out.println("findByCreatedAtGreaterThen : " + userRepository.findByCreateAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreateAtGreaterThenEqual : " + userRepository.findByCreateAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreateAtBetween(LocalDateTime.now().minusDays(1L),LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L,3L));
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L,3L));
        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin","denis")));
        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("art"));
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%art%"));
    }

    @Test
    void SortingTest(){
        System.out.println("findTop1ByName : "+ userRepository.findTop1ByName("martin"));
        System.out.println("findTop1ByNameOrderByIdDesc : "+ userRepository.findTop1ByNameOrderByIdDesc("martin"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : "+ userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));
        System.out.println("findFirstByNameWithSortParams : "+ userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"))));
        System.out.println("findFirstByNameWithSortParams : "+ userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"),Sort.Order.asc("email"))));
    }

    private Sort getSort(){
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email"),
                Sort.Order.desc("createdAt"),
                Sort.Order.desc("updatedAt")
        );
    }

    @Test
    void pagingTest(){
        System.out.println("findByNameWithPaging : "+ userRepository.findByName("martin", PageRequest.of(0,1,Sort.by(Sort.Order.desc("id")))).getContent());

    }

    @Test
    void insertAndUpdateTest(){
        User user = new User();
        user.setName("martin");
        user.setEmail("martin2@fastcampus.com");
        userRepository.save(user);
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrtin");
        userRepository.save(user2);
    }

    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);
        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);
        System.out.println(userRepository.findRowRecord().get("gender"));
    }

    @Test
    void listenerTest(){
        User user = new User();
        user.setEmail("martin@fastcampus.com");
        user.setName("martin");
        userRepository.save(user);
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("martiiiiiiin");

        userRepository.save(user2);

        userRepository.deleteById(1L);
    }

    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("martin2@fastcampus.com");
        user.setName("martin");

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("martin2@fastcampus.com"));
    }
    @Test
    void preUpdateTest(){
        User insertUser = new User();
        insertUser.setEmail("martin@fastcampus.com");
        insertUser.setName("martin");
        userRepository.save(insertUser);

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("as-is : " + user);
        user.setName("martin22");
        userRepository.save(user);

        System.out.println("to-be : " + userRepository.findAll().get(0));

    }



    @Test
    public void embedTest(){
        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("steve");
        user.setHomeAddress(new Address("서울시","강남구","강남대로 364 미왕대로","06241"));
        user.setCompanyAddress(new Address("서울시","성동구","성수대로 113 제강빌딩","04794"));
        userRepository.save(user);

        User user1 = new User();
        user1.setName("joshua");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("jordan");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());

        userRepository.save(user2);

        entityManager.clear();

        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRowRecord().forEach(a-> System.out.println(a.values()));

   }

}