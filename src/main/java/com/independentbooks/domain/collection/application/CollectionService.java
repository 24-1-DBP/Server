package com.independentbooks.domain.collection.application;

import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.collection.domain.Collection;
import com.independentbooks.domain.collection.domain.repository.CollectionRepository;
import com.independentbooks.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CollectionService {

    private final CollectionRepository collectionRepository;

    // 컬렉션 생성
    public void create(Collection collection) {
        collectionRepository.save(collection);
    }

    // Id로 컬렉션 조회
    public Collection findById(Long id) {
        return collectionRepository.findCollection(id);
    }

    // 모든 컬렉션 조회
    public List<Collection> findAll() {
        return collectionRepository.findAllCollection();
    }

    // 특정 유저의 컬렉션 조회
    public List<Collection> findAllByUser(User user) {
        return collectionRepository.findAllByUser(user);
    }

    // 컬렉션 수정
    public Collection modify(Long id, String collection_name, String newDescription, List<Book> books) {
        Collection findCollection = collectionRepository.findCollection(id);
        findCollection.setDescription(newDescription);
        findCollection.setBooks(books);
        findCollection.setCollection_name(collection_name);

        return findCollection;
    }

    // 컬렉션 삭제
    public void delete(Collection collection) {
        collectionRepository.deleteCollection(collection);
    }

}
