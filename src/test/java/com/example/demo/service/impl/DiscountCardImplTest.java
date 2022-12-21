package com.example.demo.service.impl;

import com.example.demo.dto.DiscountCardDto;
import com.example.demo.dto.converter.DiscountCardConverter;
import com.example.demo.exception.DiscountCardNotFoundException;
import com.example.demo.exception.repositories.DiscountCardRepository;
import com.example.demo.model.discount.DiscountCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
class DiscountCardImplTest {

    @InjectMocks
    private DiscountCardImpl cardService;

    @Mock
    private DiscountCardRepository cardRepository;

    @Test
    void getDiscountCardByIdWhenCardIsExist() {

        long cardId = 1;
        when(cardRepository.findById(cardId))
                .thenReturn(Optional.ofNullable(createCard()));
        DiscountCardDto card = cardService.getDiscountCardById(cardId);

        assertNotNull(card);
        assertNotNull(card.getDiscount());
        assertEquals(5, card.getDiscount());

    }

    @Test
    void getDiscountCardByIdWhenCardIsNotExist() {
        DiscountCardImpl cardService =new DiscountCardImpl (cardRepository, new DiscountCardConverter());

        long cardId = 1;
        when(cardRepository.findById(cardId))
                .thenReturn(Optional.ofNullable(null));

        Throwable exception = assertThrows(DiscountCardNotFoundException.class, () -> cardService.getDiscountCardById(cardId));

        assertNotNull(exception);
        assertEquals("Discount Card with id '1' not found", exception.getMessage());

    }

    private static DiscountCard createCard() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setId(1L);
        discountCard.setDiscount(5);
        return discountCard;
    }
}