package com.independentbooks.domain.like.domain.repository;

import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.like.domain.Like;
import com.independentbooks.domain.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Long countByBook(Book book);

    @Query("SELECT l.book, COUNT(l) as likeCount FROM Like l WHERE l.createdAt >= :oneMonthAgo GROUP BY l.book ORDER BY likeCount DESC")
    List<Object[]> findTopBooksByLikesWithinOneMonth(@Param("oneMonthAgo") LocalDateTime oneMonthAgo, Pageable pageable);


    void deleteByBookAndUser(Book book, User user);
    boolean existsByBookAndUser(Book book, User user);
}