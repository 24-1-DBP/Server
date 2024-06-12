package com.independentbooks.domain.collection.domain.repository;

import com.independentbooks.domain.collection.domain.Collection;
import com.independentbooks.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findAllByUser(User user);

    Optional<List<Collection>> findByUser(User user);
}

