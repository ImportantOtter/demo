package com.example.demo.dto.converter;

import com.example.demo.dto.DiscountCardDto;
import com.example.demo.model.discount.DiscountCard;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DiscountCardConverter {

    private final ModelMapper modelMapper;

    public DiscountCardConverter() {
        this.modelMapper = new ModelMapper();
    }

    public DiscountCardDto convertToDto(DiscountCard discountCard) {
        return modelMapper.map(discountCard, DiscountCardDto.class);
    }
}
