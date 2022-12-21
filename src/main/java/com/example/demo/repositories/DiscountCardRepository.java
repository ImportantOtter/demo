package com.example.demo.exception.repositories;

import com.example.demo.model.discount.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Long> {
}
