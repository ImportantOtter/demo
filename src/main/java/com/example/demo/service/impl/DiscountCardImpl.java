package com.example.demo.service.impl;

import com.example.demo.dto.DiscountCardDto;
import com.example.demo.dto.converter.DiscountCardConverter;
import com.example.demo.exception.DiscountCardNotFoundException;
import com.example.demo.exception.repositories.DiscountCardRepository;
import com.example.demo.service.DiscountCardService;
import org.springframework.stereotype.Service;

@Service
public class DiscountCardImpl implements DiscountCardService {

    private DiscountCardRepository discountCardRepository;
    private DiscountCardConverter discountCardConverter;

    public DiscountCardImpl(DiscountCardRepository discountCardRepository, DiscountCardConverter discountCardConverter) {
        this.discountCardRepository = discountCardRepository;
        this.discountCardConverter = discountCardConverter;
    }

    @Override
    public DiscountCardDto getDiscountCardById(long id) {
        return discountCardRepository.findById(id)
                .map(discountCardConverter::convertToDto)
                .orElseThrow(() -> new DiscountCardNotFoundException(String.format("Discount Card with id '%d' not found", id)));

    }
}

