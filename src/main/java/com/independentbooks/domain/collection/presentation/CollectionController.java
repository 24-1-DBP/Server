package com.independentbooks.domain.collection.presentation;

import com.independentbooks.domain.book.application.BookService;
import com.independentbooks.domain.collection.application.CollectionService;
import com.independentbooks.domain.collection.dto.request.CreateCollectionRequest;
import com.independentbooks.domain.collection.dto.request.UpdateCollectionRequest;
import com.independentbooks.domain.content.application.ContentService;
import com.independentbooks.domain.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
public class CollectionController {

    private final CollectionService collectionService;
    private final BookService bookService;
    private final UserService userService;
    private final ContentService contentService;

    // 모든 컬렉션 조회
    @GetMapping("")
    public ResponseEntity<?> getCollections() {
        return ResponseEntity.ok(collectionService.findAll());
    }

    //특정 컬렉션 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> findCollection(@PathVariable("id") Long id) {
        return ResponseEntity.ok(collectionService.findById(id));
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateCollectionRequest request) {
        return ResponseEntity.ok(collectionService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(collectionService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UpdateCollectionRequest request) {
        return ResponseEntity.ok(collectionService.update(id, request));
    }
}

