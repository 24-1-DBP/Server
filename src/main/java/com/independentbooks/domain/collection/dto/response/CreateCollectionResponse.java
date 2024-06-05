package com.independentbooks.domain.collection.dto.response;

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
public class CreateCollectionResponse {
    private Long collection_id;
    private String collection_name;
    private String user_nickname;
    private String description;
    private List<Book> books;
}
