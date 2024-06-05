package com.independentbooks.domain.collection.presentation;

import com.independentbooks.domain.book.application.BookService;
import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.collection.application.CollectionService;
import com.independentbooks.domain.collection.domain.Collection;
import com.independentbooks.domain.collection.dto.request.CollectionModifyRequest;
import com.independentbooks.domain.collection.dto.request.CollectionWithBooks;
import com.independentbooks.domain.collection.dto.request.CreateCollectionRequest;
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
        return ResponseEntity.ok(collections);
    }

    // Id가 1인 유저의 컬렉션 조회
    @GetMapping("/user/1")
    public ResponseEntity<List<Collection>> userCollection() {
        User findUser = userService.findUserById(1L).orElse(null); // 값이 없으면 null 반환
        List<Collection> collections = collectionService.findAllByUser(findUser);
        return ResponseEntity.ok(collections);
    }

    // 컬렉션 생성 폼 데이터를 반환
    @GetMapping("/new")
    public ResponseEntity<List<Book>> createForm() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
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

        Collection collection = Collection.createCollection(findUser, content, request.getDescription(), books);
        collectionService.create(collection);
        return ResponseEntity.ok().build();
    }

    // 컬렉션 수정 버튼 클릭시 수정 폼을 보여줌
    @GetMapping("/{id}")
    public ResponseEntity<CollectionWithBooks> modifyForm(@PathVariable("id") Long id) {
        Collection collection = collectionService.findById(id);
        List<Book> books = bookService.findAll();
        CollectionWithBooks collectionWithBooks = new CollectionWithBooks(collection, books);
        return ResponseEntity.ok(collectionWithBooks);
    }

    // 컬렉션 수정 완료 버튼 클릭시
    @PostMapping
    public ResponseEntity<Collection> modify(@RequestBody CollectionModifyRequest request) {

        List<Book> books = new ArrayList<>();
        for(Long id: request.getIds()){
            books.add(bookService.findById(id));
        }

        Collection modifiedCollection = collectionService.modify(request.getId(), request.getDescription(), books);
        return ResponseEntity.ok(modifiedCollection);
    }

    // 컬렉션 삭제 버튼 클릭시
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Collection findCollection = collectionService.findById(id);
        collectionService.delete(findCollection);
        return ResponseEntity.ok().build();
    }
}

