package com.independentbooks.domain.collection.dto.response;

import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.book.dto.response.BookListRes;
import com.independentbooks.domain.collection.domain.Collection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CollectionDTO {
    private Long id;

    private String collection_name;
    private String username;

    private List<BookListRes> books = new ArrayList<>();

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public CollectionDTO(Collection collection) {
        this.id = collection.getId();
        this.collection_name = collection.getCollection_name();
        this.description = collection.getDescription();
        this.createdDate = collection.getCreatedDate();
        this.updatedDate = collection.getUpdatedDate();
        if (collection.getUser() != null) {
            this.username = collection.getUser().getNickname();
        }
        if (collection.getBooks() != null) {
            for (int i = 0; i < collection.getBooks().size(); i++) {
                Book book = collection.getBooks().get(i);
                BookListRes BookDTO = BookListRes.builder().
                        bookId(book.getBookId())
                        .title(book.getTitle())
                        .image(book.getImage())
                        .price(book.getPrice())
                        .build();
                this.books.add(BookDTO);
            }
        }
    }
}
