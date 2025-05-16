package com.example.jpa;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;

@RestController
public class EntityManagerProductController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("v2/products")
    @Transactional(noRollbackFor = {Exception.class, RuntimeException.class})
    public ResponseEntity<?> save(@RequestBody Product entity) {
        try {
            entityManager.persist(entity);
        } catch (Exception e) {
            entity.setId(UUID.randomUUID());
            entityManager.persist(entity);
        }
        return ResponseEntity.ok().build();
    }
}