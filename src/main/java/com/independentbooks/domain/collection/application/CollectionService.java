package com.independentbooks.domain.collection.application;

import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.book.domain.repository.BookRepository;
import com.independentbooks.domain.collection.domain.Collection;
import com.independentbooks.domain.collection.domain.repository.CollectionRepository;
import com.independentbooks.domain.collection.dto.request.CreateCollectionRequest;
import com.independentbooks.domain.collection.dto.request.UpdateCollectionRequest;
import com.independentbooks.domain.collection.dto.response.OkResponse;
import com.independentbooks.domain.user.domain.User;
import com.independentbooks.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    // 컬렉션 생성
    @Transactional
    public ResponseEntity<Collection> create(CreateCollectionRequest request) {
        try {
            List<Book> bookList = new ArrayList<>();

            for (int i = 0; i < request.getBooks().size(); i++) {
                Book book = bookRepository.findById(request.getBooks().get(i)).orElseThrow(() -> new NotFoundException("cannot find book"));
                bookList.add(book);
            }
            User user = userRepository.findById(request.getUserID()).orElseThrow(() -> new NotFoundException("cannot find user"));

            Collection paramCollection = Collection.builder().
                    collection_name(request.getCollection_name())
                    .description(request.getDescription())
                    .books(bookList)
                    .user(user)
                    .build();
            Collection newCollection = collectionRepository.save(paramCollection);
            return ResponseEntity.ok(newCollection);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Id로 컬렉션 조회
    @Transactional
    public ResponseEntity<Collection> findById(Long id) {
        try {
            return ResponseEntity.ok(collectionRepository.findById(id).orElseThrow(() -> new NotFoundException("cannot find collection")));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 모든 컬렉션 조회
    @Transactional
    public ResponseEntity<List<Collection>> findAll() {
        return ResponseEntity.ok(collectionRepository.findAll());
    }

    @Transactional
    public ResponseEntity<List<Collection>> findByUserID(Long userID) {
        try {
            User user = userRepository.findById(userID).orElseThrow(() -> new NotFoundException("cannot find user"));
            return ResponseEntity.ok(collectionRepository.findByUser(user).orElseThrow(() -> new NotFoundException("cannot find collection")));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<Collection> update(Long id, UpdateCollectionRequest request) {
        try {
            Collection collection = collectionRepository.findById(id).orElseThrow(() -> new NotFoundException("cannot find collection"));
            if (request.getCollection_name() != null) {
                collection.setCollection_name(request.getCollection_name());
            }

            if (request.getDescription() != null) {
                collection.setDescription(request.getDescription());
            }

            if (request.getBookIds() != null) {
                List<Book> bookList = new ArrayList<>();

                for (int i = 0; i < request.getBookIds().size(); i++) {
                    Book book = bookRepository.findById(request.getBookIds().get(i)).orElseThrow(() -> new NotFoundException("cannot find book"));
                    bookList.add(book);
                }
                collection.setBooks(bookList);
            }
            return ResponseEntity.ok(collection);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<OkResponse> delete(Long id) {
        try {
            Collection collection = collectionRepository.findById(id).orElseThrow(() -> new NotFoundException("cannot find collection"));
            collectionRepository.delete(collection);
            return ResponseEntity.ok(new OkResponse(true));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
