package com.independentbooks.domain.collection.dto.request;

import com.independentbooks.domain.book.domain.Book;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCollectionRequest {
//    private List<Book> books;
    private List<Long> ids;
    private String description;
}
