package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;
    private double price;
    private int quantity;
    private Boolean promotionalProduct;

    @Override
    public String toString() {
        return quantity + "\t" + name + "\t" + price + "\t" + price * quantity;
    }

    public double getTotalPrice(){
        return price * quantity;
    }

    public String toHtmlString() {
        return quantity + " &emsp; " + name + " &emsp; " + price + " &emsp; " + price * quantity;
    }
}
