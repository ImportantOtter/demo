package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.converter.ProductConverter;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.repositories.ProductRepository;
import com.example.demo.model.products.Product;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    ProductRepository repository;

    ProductServiceImpl service;

    @Test
    void getProductByIdWhenExistInDB() {
        this.service = new ProductServiceImpl(repository, new ProductConverter());

        Product p = new Product(0L, "Name", 0.0, true);
        when(repository.findById(0L)).thenReturn(Optional.of(p));
        ProductDto productById = service.getProductById(0);
        assertNotNull(productById);
        assertEquals(0L, p.getId());
        assertEquals(0.0, p.getPrice());
        assertEquals("Name", p.getName());
        assertTrue(p.getPromotionalProduct());
    }

    @Test
    void getProductByIdWhenNotExistInDB() {
        this.service = new ProductServiceImpl(repository, new ProductConverter());
        when(repository.findById(0L)).thenReturn(Optional.ofNullable(null));

        Throwable exception = assertThrows(ProductNotFoundException.class, () -> service.getProductById(0));

        assertNotNull(exception);
        assertEquals("Product with id '0' not found", exception.getMessage());
    }
}