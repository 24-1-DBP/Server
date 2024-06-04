package com.independentbooks.collection;

import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.collection.application.CollectionService;
import com.independentbooks.domain.collection.domain.Collection;
import com.independentbooks.domain.collection.domain.repository.CollectionRepository;
import com.independentbooks.domain.content.domain.Content;
import com.independentbooks.domain.content.domain.ContentType;
import com.independentbooks.domain.user.domain.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

@SpringBootTest
@Transactional
public class CollectionEntityTest {

    @Autowired
    EntityManager em;

    @Autowired
    CollectionService collectionService;

    @Autowired
    CollectionRepository collectionRepository;


    @Test
    void collectionCreateTest() {
        User user = new User("userA", "testUser");
        em.persist(user);

        Content content = new Content();
        content.setContentType(ContentType.COLLECTION);
        em.persist(content);

        Book book = new Book("JPA", "kim", "kim_pub", "ISBN_Test", "Info_test", 3000L, LocalDateTime.of(2024, 6, 4, 21, 15, 30), "이미지");
        em.persist(book);

        List<Book> books = new ArrayList<>();
        books.add(book);

        Collection collection = Collection.createCollection(user, content, "테스트 컬렉션", books);
        em.persist(collection);

        List<Collection> collections = collectionService.findAll();
        System.out.println(collections.size());
        System.out.println(collections.get(0).getDescription());
        System.out.println(collections.get(0).getId());

    }
}
