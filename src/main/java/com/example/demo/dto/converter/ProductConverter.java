package com.example.demo.dto.converter;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.products.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    private final ModelMapper modelMapper;

    public ProductConverter() {
        this.modelMapper = new ModelMapper();
    }

    public ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
