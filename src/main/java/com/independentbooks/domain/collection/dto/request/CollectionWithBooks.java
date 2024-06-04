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
    private Collection collection;
    private List<Book> books;
}
