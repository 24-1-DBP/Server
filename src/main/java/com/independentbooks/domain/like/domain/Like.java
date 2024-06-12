package com.independentbooks.domain.like.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.common.BaseEntity;
import com.independentbooks.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Likes")
@NoArgsConstructor
@Getter
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Like(Book book, User user) {
        this.book = book;
        this.user = user;
    }
}
