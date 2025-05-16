package com.example.jpa;

import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class JPAProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping("/products")
    @Transactional(noRollbackFor = RuntimeException.class)
    public ResponseEntity<?> save(@Valid @RequestBody Product entity) {
        try {
            repository.save(entity);
            throw new RuntimeException("Triggered Rollback");
        } catch (ConstraintViolationException e) {
            entity.setId(UUID.randomUUID());
            entity.setPrice(1.0);
            repository.save(entity);
        }
        return ResponseEntity.ok().build();
    }
    
    
}