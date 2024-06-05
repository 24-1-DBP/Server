package com.independentbooks.domain.collection.dto.request;

import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.collection.domain.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionWithBooks {

    private Long collection_id;
    private String collection_name;
    private String description;
    private List<Long> collection_book_ids; //현재 컬렉션에 포함된 책들의 ids
    private List<Long> all_book_ids; //모든 책들의 ids

}
