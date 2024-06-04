package com.independentbooks.domain.collection.dto.request;

import com.independentbooks.domain.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionModifyRequest {
    private Long id;
    private String description;
    private List<Book> books;
}
