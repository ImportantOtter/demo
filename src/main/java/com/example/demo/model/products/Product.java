package com.example.demo.model.products;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name")
    @NotNull
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "price")
    @NotNull
    @EqualsAndHashCode.Include
    private double price;

    @Column(name = "promotional_product")
    @NotNull
    @EqualsAndHashCode.Include
    private Boolean promotionalProduct;
}
