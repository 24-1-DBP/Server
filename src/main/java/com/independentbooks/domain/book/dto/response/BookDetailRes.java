package com.independentbooks.domain.book.dto.response;
import com.independentbooks.domain.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailRes {
    // 표지,제목,가격,줄거리,저자,출판사,출판일,ISBN,리뷰목록
    private String image;
    private String title;
    private Long price;
    private String summary;
    private String author;
    private String publisher;
    private String pubDate;
    private String isbn;
    private List<ReviewRes> reviews;
    private Boolean isLiked;

}
