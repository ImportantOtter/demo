package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.converter.ProductConverter;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.repositories.ProductRepository;
import com.example.demo.model.products.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductConverter productConverter;

    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public ProductDto getProductById(long id) {
        return productRepository.findById(id)
                .map(productConverter::convertToDto)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id '%d' not found", id)));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
