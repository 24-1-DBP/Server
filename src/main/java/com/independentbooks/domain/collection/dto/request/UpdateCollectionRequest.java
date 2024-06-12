package com.independentbooks.domain.collection.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCollectionRequest {
    private String collection_name;
    private String description;
    private List<Long> bookIds;
}
