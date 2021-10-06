package com.fastcampus.jpa.FastCampusJPA06.repository;

import com.fastcampus.jpa.FastCampusJPA06.domain.Book;
import com.fastcampus.jpa.FastCampusJPA06.repository.dto.BookNameAndCategory;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Modifying
    @Query(value = "update book set category='none'",nativeQuery = true)
    public void update();

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtGreaterThanEqual(String name, LocalDateTime createdAt,LocalDateTime updatedAt);

    @Query(value = "SELECT b FROM Book b"
            + " WHERE NAME = :name AND CREATE_AT >= :createAt AND UPDATE_AT >= :updateAt AND CATEGORY IS NULL")
    List<Book> findByNameRecently(@Param("name") String name,@Param("createAt") LocalDateTime createdAt,@Param("updateAt") LocalDateTime updatedAt);

    @Query(value = "select new com.fastcampus.jpa.FastCampusJPA06.repository.dto.BookNameAndCategory(b.name , b.category) from Book b")//JQQL이기에 가능하다(자바 문법이 들어간다)
    List<BookNameAndCategory> findBookNameAndCategory();

    @Query(value = "select new com.fastcampus.jpa.FastCampusJPA06.repository.dto.BookNameAndCategory(b.name , b.category) from Book b")
    Page<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);


    //Native쿼리일 경우 java 문법을 사용 x,스네이크 케이스로 표기 해야된다.
    @Query(value = "SELECT * FROM book" ,nativeQuery = true)
    List<Book> findAllCustom();

    @Transactional
    @Modifying
    @Query(value = "update book set category='IT전문서'",nativeQuery = true)
    int updateCategories();

    @Query(value = "show tables", nativeQuery = true)
    List<String> showTables();

    @Query(value = "SELECT * FROM book ORDER BY id DESC limit 1",nativeQuery = true)
    Map<String,Object> findRowRecord();
}
