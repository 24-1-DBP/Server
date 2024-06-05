package com.independentbooks.domain.collection.domain;

import com.independentbooks.domain.user.domain.User;
import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.content.domain.Content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long id;

    private String collection_name;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    // Book과 N:N 관계
    @ManyToMany
    @JoinTable(
            name = "collection_book",
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "content_id")
    private Content content;

    private String description;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedDate;

    //== 연관관계 메서드 ==//
    public void addBooks(Book book) {
        books.add(book);
    }

    // 생성 메서드
    public static Collection createCollection(String collection_name, User user, Content content, String description, List<Book> books) {
        Collection collection = new Collection();
        collection.setUser(user);
        for (Book book : books) {
            collection.addBooks(book);
        }
        collection.setContent(content);
        collection.setDescription(description);
        collection.setCollection_name(collection_name);

        return collection;
    }

}
