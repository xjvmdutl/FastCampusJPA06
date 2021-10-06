package com.fastcampus.jpa.FastCampusJPA06.repository;

import com.fastcampus.jpa.FastCampusJPA06.domain.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select distinct r from Review r join fetch r.comments")
    List<Review> findAllByFetchJoin();

    @EntityGraph(attributePaths = "comments")//Comments에 대해 EntityGraph를 직접 그리겠다
    @Query("select r from Review r")
    List<Review> findAllByEntityGraph();

    @EntityGraph(attributePaths = "comments")//Comments에 대해 EntityGraph를 직접 그리겠다
    List<Review> findAll();//기존 jpa메소드를 오버라이드 해서 할수 있다.
}
