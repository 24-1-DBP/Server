package com.independentbooks.domain.collection.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCollectionResponse {
    private Long collection_id;
    private String collection_name;
    private String user_nickname;
    private String description;
}
