package com.fastcampus.jpa.FastCampusJPA06.repository;

import com.fastcampus.jpa.FastCampusJPA06.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface UserRepository extends JpaRepository<User,Long> {
    Set<User> findByName(String name);

    Set<User> findUserByNameIs(String name);
    Set<User> findUserByName(String name);
    Set<User> findUserByNameEquals(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String email);

    List<User> findFirst2ByName(String name);
    List<User> findTop2ByName(String name);

    List<User> findLast1ByName(String name);

    List<User> findByEmailAndName(String email,String name);

    List<User> findByEmailOrName(String email,String name);

    List<User> findByCreateAtAfter(LocalDateTime yesterday);

    List<User> findByIdAfter(Long id);

    List<User> findByCreateAtGreaterThan(LocalDateTime yesterday);

    List<User> findByCreateAtGreaterThanEqual(LocalDateTime yesterday);

    List<User> findByCreateAtBetween(LocalDateTime yesterday,LocalDateTime tomorrow);

    List<User> findByIdBetween(Long id1,Long id2);

    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1,Long id2);

    List<User> findByIdIsNotNull();

    List<User> findByNameIn(List<String> names);//매개변수로 Iterator 타입이 들어가야된다.

    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);

    List<User> findByNameLike(String name);


    List<User> findTop1ByName(String name);

    List<User> findTop1ByNameOrderByIdDesc(String name);

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<User> findFirstByName(String name, Sort sort);

    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1",nativeQuery = true)
    Map<String,Object> findRowRecord();


    @Query(value = "Select * from user",nativeQuery = true)
    List<Map<String,Object>> findAllRowRecord();
}
