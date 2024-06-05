package com.independentbooks.domain.like.application;

import com.independentbooks.domain.book.application.BookService;
import com.independentbooks.domain.book.domain.Book;
import com.independentbooks.domain.book.domain.repository.BookRepository;
import com.independentbooks.domain.like.domain.Like;
import com.independentbooks.domain.like.domain.repository.LikeRepository;
import com.independentbooks.domain.user.domain.User;
import com.independentbooks.domain.user.domain.repository.UserRepository;
import com.independentbooks.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> increaseLike(Long bookId) {
        Long userId = 1L;
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));

        if(likeRepository.existsByBookAndUser(book,user)){
            likeRepository.deleteByBookAndUser(book,user);
            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information("좋아요 삭제 성공")
                    .build();
            return ResponseEntity.ok(apiResponse);
        }
        else {
            Like like= Like.builder()
                    .book(book)
                    .user(user)
                    .build();
            likeRepository.save(like);

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information("좋아요 추가 성공")
                    .build();
            return ResponseEntity.ok(apiResponse);
        }

    }


}
