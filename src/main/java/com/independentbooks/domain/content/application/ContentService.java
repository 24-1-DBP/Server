package com.independentbooks.domain.content.application;

import com.independentbooks.domain.content.domain.Content;
import com.independentbooks.domain.content.domain.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public Content find(Long id) {
        return contentRepository.findContent(id);
    }
}
