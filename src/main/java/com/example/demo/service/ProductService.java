package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.products.Product;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(long id);
    List<Product> getAllProducts();

}
