package com.independentbooks.domain.collection.dto.request;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCollectionRequest {
    private List<Long> books;
    private Long userID;
    private String collection_name;
    private String description;
}
