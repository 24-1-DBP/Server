package com.independentbooks.domain.book.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.independentbooks.domain.common.BaseEntity;
import com.independentbooks.domain.like.domain.Like;
import com.independentbooks.domain.review.domain.Review;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Book")
@NoArgsConstructor
@Getter
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title", nullable = false) //도서 제목
    private String title;

    @Column(name = "author", nullable = false) //저자
    private String author;

    @Column(name = "publisher", nullable = false) //출판사
    private String publisher;

    @Column(name = "isbn", nullable = false, unique = true) //ISBN
    private String isbn;

    // 타입 Text로 지정
    @Column(name = "book_info", columnDefinition = "TEXT", nullable = false) //도서 정보
    private String bookInfo;

    @Column(name = "price", nullable = false) //도서 가격
    private Long price;

    @Column(name = "published_date", nullable = false) //출간일
    private LocalDateTime publishedDate;

    @Column(name = "image", nullable = false) //도서 표지 이미지
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "book")
    private List<Like> likes;

    // 컬렉션과 N:N 관계
//     @ManyToMany(mappedBy = "books")
//     private List<Collection> collections = new ArrayList<>();

    @Builder
    public Book(String title, String author, String publisher, String isbn, String bookInfo, Long price, LocalDateTime publishedDate, String image) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.bookInfo = bookInfo;
        this.price = price;
        this.publishedDate = publishedDate;
        this.image = image;
    }
}
