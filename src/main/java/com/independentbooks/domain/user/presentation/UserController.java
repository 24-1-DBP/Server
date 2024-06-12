package com.independentbooks.domain.user.presentation;

import com.independentbooks.domain.collection.application.CollectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final CollectionService collectionService;

    @GetMapping("/{id}/collections")
    public ResponseEntity<?> findUserCollection(@PathVariable("id") Long id) {
        return ResponseEntity.ok(collectionService.findByUserID(id));
    }
}
