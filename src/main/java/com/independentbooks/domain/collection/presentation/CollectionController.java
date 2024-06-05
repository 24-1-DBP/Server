package com.independentbooks.domain.collection.presentation;

import com.independentbooks.domain.book.application.BookService;
import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.collection.application.CollectionService;
import com.independentbooks.domain.collection.domain.Collection;
import com.independentbooks.domain.collection.dto.request.CollectionModifyRequest;
import com.independentbooks.domain.collection.dto.request.CollectionWithBooks;
import com.independentbooks.domain.collection.dto.request.CreateCollectionRequest;
import com.independentbooks.domain.collection.dto.response.CreateCollectionResponse;
import com.independentbooks.domain.content.application.ContentService;
import com.independentbooks.domain.content.domain.Content;
import com.independentbooks.domain.content.domain.ContentType;
import com.independentbooks.domain.user.application.UserService;
import com.independentbooks.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;
    private final BookService bookService;
    private final UserService userService;
    private final ContentService contentService;

    // 모든 컬렉션 조회
    @GetMapping("/all")
    public ResponseEntity<?> getCollections() {
        List<Collection> collections = collectionService.findAll();

        List<CreateCollectionResponse> responses = new ArrayList<>();

        for (Collection c : collections) {
            List<Long> book_ids = new ArrayList<>();  // 각 컬렉션마다 새로운 리스트 생성
            for (Book b : c.getBooks()) {
                book_ids.add(b.getBookId());
            }
            CreateCollectionResponse collectionResponse = new CreateCollectionResponse(
                    c.getId(),
                    c.getCollection_name(),
                    c.getUser().getNickname(),
                    c.getDescription(),
                    book_ids
            );
            responses.add(collectionResponse);
        }

        return ResponseEntity.ok(responses);
    }

    //특정 컬렉션 조회
    @GetMapping("/collection/{id}")
    public ResponseEntity<?> findCollection(@PathVariable("id") Long id) {
        Collection c = collectionService.findById(id);

        List<Long> book_ids = new ArrayList<>();
        for (Book b : c.getBooks()) {
            book_ids.add(b.getBookId());
        }
        CreateCollectionResponse collectionResponse = new CreateCollectionResponse(c.getId(), c.getCollection_name(), c.getUser().getNickname(), c.getDescription(), book_ids);
        return ResponseEntity.ok(collectionResponse);
    }


    // Id가 1인 유저의 컬렉션 조회
    @GetMapping("/user/1")
    public ResponseEntity<?> userCollection() {
        User findUser = userService.findUserById(1L).orElse(null); // 값이 없으면 null 반환
        List<Collection> collections = collectionService.findAllByUser(findUser);
        List<CreateCollectionResponse> responses = new ArrayList<>();

        for (Collection c : collections) {
            List<Long> book_ids = new ArrayList<>(); // 각 컬렉션마다 새로운 리스트 생성
            for (Book b : c.getBooks()) {
                book_ids.add(b.getBookId());
            }
            CreateCollectionResponse collectionResponse = new CreateCollectionResponse(
                    c.getId(),
                    c.getCollection_name(),
                    c.getUser().getNickname(),
                    c.getDescription(),
                    book_ids
            );
            responses.add(collectionResponse);
        }

        return ResponseEntity.ok(responses);
    }

    // 컬렉션 생성 폼 데이터를 반환
    @GetMapping("/new")
    public ResponseEntity<?> createForm() {
        List<Book> books = bookService.findAll();

        List<Long> book_ids = new ArrayList<>();
        for (Book b : books) {
            book_ids.add(b.getBookId());
        }
        return ResponseEntity.ok(book_ids);
    }

    // 컬렉션 생성 완료 버튼 클릭시
    @PostMapping("/new")
    public ResponseEntity<Void> create(@RequestBody CreateCollectionRequest request) {
        // ID가 1인 유저 조회
        User findUser = userService.findUserById(1L).orElse(null);
        Content content = contentService.find(1L);

        List<Book> books = new ArrayList<>();
        for(Long id: request.getIds()){
            books.add(bookService.findById(id));
        }

        Collection collection = Collection.createCollection(request.getCollection_name(), findUser, content, request.getDescription(), books);
        collectionService.create(collection);
        return ResponseEntity.ok().build();
    }

    // 컬렉션 수정 버튼 클릭시 수정 폼을 보여줌
    @GetMapping("/{id}")
    public ResponseEntity<CollectionWithBooks> modifyForm(@PathVariable("id") Long id) {
        Collection collection = collectionService.findById(id);
        List<Book> books = bookService.findAll();

        List<Long> collection_books_ids = new ArrayList<>();
        List<Long> all_books_ids = new ArrayList<>();

        for (Book b : collection.getBooks()) {
            collection_books_ids.add(b.getBookId());
        }
        for (Book b : books) {
            all_books_ids.add(b.getBookId());
        }

        CollectionWithBooks collectionWithBooks = new CollectionWithBooks(
                collection.getId(),
                collection.getCollection_name(),
                collection.getDescription(),
                collection_books_ids,
                all_books_ids
        );

        return ResponseEntity.ok(collectionWithBooks);
    }

    // 컬렉션 수정 완료 버튼 클릭시
    @PostMapping
    public ResponseEntity<?> modify(@RequestBody CollectionModifyRequest request) {

        List<Book> books = new ArrayList<>();
        for(Long id: request.getIds()){
            books.add(bookService.findById(id));
        }

        Collection modifiedCollection = collectionService.modify(request.getId(), request.getCollection_name(), request.getDescription(), books);

        List<Long> book_ids = new ArrayList<>();
        for (Book b : modifiedCollection.getBooks()) {
            book_ids.add(b.getBookId());
        }

        CreateCollectionResponse collectionResponse = new CreateCollectionResponse(
                modifiedCollection.getId(),
                modifiedCollection.getCollection_name(),
                modifiedCollection.getUser().getNickname(),
                modifiedCollection.getDescription(),
                book_ids
        );

        return ResponseEntity.ok(collectionResponse);
    }

    // 컬렉션 삭제 버튼 클릭시
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Collection findCollection = collectionService.findById(id);
        collectionService.delete(findCollection);
        return ResponseEntity.ok().build();
    }
}

