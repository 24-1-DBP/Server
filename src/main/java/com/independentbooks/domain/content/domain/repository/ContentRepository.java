package com.independentbooks.domain.content.domain.repository;

import com.independentbooks.domain.content.domain.Content;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContentRepository {

    private final EntityManager em;

    public Content findContent(Long id) {
        return em.find(Content.class, id);
    }
}
