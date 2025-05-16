//package com.example.jpa;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.transaction.annotation.Transactional;
//
//import jakarta.validation.ConstraintViolationException;
//import java.util.List;
//import java.util.UUID;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class ProductRepositoryTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Test
//    public void whenFindById_thenReturnProduct() {
//        // Given
//        Product product = new Product(UUID.randomUUID(), "Test Product", 19.99, 100);
//        entityManager.persist(product);
//        entityManager.flush();
//
//        // When
//        Product found = productRepository.findById(product.getId()).orElse(null);
//
//        // Then
//        assertThat(found).isNotNull();
//        assertThat(found.getName()).isEqualTo(product.getName());
//    }
//
//    @Test
//    public void whenInvalidName_thenThrowConstraintViolation() {
//        // Given
//        Product product = new Product(UUID.randomUUID(), "", -10.0, -5);
//
//        // When/Then
//        assertThrows(ConstraintViolationException.class, () -> {
//            entityManager.persist(product);
//            entityManager.flush();
//        });
//    }
//
//    @Test
//    public void whenDuplicateId_thenThrowException() {
//        // Given
//        UUID id = UUID.randomUUID();
//        Product product1 = new Product(id, "Product 1", 10.0, 100);
//        Product product2 = new Product(id, "Product 2", 20.0, 200);
//
//        // When
//        entityManager.persist(product1);
//        entityManager.flush();
//
//        // Then
//        assertThrows(Exception.class, () -> {
//            entityManager.persist(product2);
//            entityManager.flush();
//        });
//    }
//
//    @Test
//    public void whenFindByPriceGreaterThan_thenReturnFilteredProducts() {
//        // Given
//        Product p1 = new Product(UUID.randomUUID(), "Cheap", 9.99, 10);
//        Product p2 = new Product(UUID.randomUUID(), "Expensive", 99.99, 5);
//        entityManager.persist(p1);
//        entityManager.persist(p2);
//        entityManager.flush();
//
//        // When
//        List<Product> expensiveProducts = productRepository.findByPriceGreaterThan(50.0);
//
//        // Then
//        assertThat(expensiveProducts).hasSize(1);
//        assertThat(expensiveProducts.get(0).getName()).isEqualTo("Expensive");
//    }
//
//    @Test
//    @Transactional
//    public void whenUpdateStockQuantity_thenPersisted() {
//        // Given
//        Product product = new Product(UUID.randomUUID(), "Test", 10.0, 100);
//        entityManager.persist(product);
//        entityManager.flush();
//
//        // When
//        product.setStockQuantity(50);
//        entityManager.flush();
//        Product updated = entityManager.find(Product.class, product.getId());
//
//        // Then
//        assertThat(updated.getStockQuantity()).isEqualTo(50);
//    }
//
//    @Test
//    public void whenExistsByName_thenReturnBoolean() {
//        // Given
//        Product product = new Product(UUID.randomUUID(), "Unique Product", 10.0, 100);
//        entityManager.persist(product);
//        entityManager.flush();
//
//        // When/Then
//        assertTrue(productRepository.existsByName("Unique Product"));
//        assertFalse(productRepository.existsByName("Nonexistent Product"));
//    }
//}