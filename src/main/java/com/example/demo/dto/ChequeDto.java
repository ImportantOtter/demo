package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChequeDto {

    private Long id;
    private String cashier;
    private String address;
    private String phone;
    private LocalDateTime orderDate;
    private List<ProductDto> productsList;
    private double totalPrice;
    private double discountPercentage;

    private double discountCard;

    public ChequeDto(Long id, String cashier, String address, String phone, LocalDateTime orderDate, List<ProductDto> productsList, double totalPrice, double discountPercentage, double discountCard) {
        this.id = id;
        this.cashier = cashier;
        this.address = address;
        this.phone = phone;
        this.orderDate = orderDate;
        this.productsList = productsList;
        this.totalPrice = totalPrice;
        this.discountPercentage = discountPercentage;
        this.discountCard = discountCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<ProductDto> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductDto> productsList) {
        this.productsList = productsList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(double discountCard) {
        this.discountCard = discountCard;
    }

    public ChequeDto() {
        this.productsList = new ArrayList<>();
    }

    public void addProduct(ProductDto product) {
        this.productsList.add(product);
    }

    @Override
    public String toString() {

        StringBuilder response = new StringBuilder();
        String newLine = "\n";
        String tab = "\t";
        response.append("<========================> " + newLine);
        response.append(" CASH RECEIPT " + newLine); //
        response.append("Adress: " + getAddress() + newLine);
        response.append("Number: " + getPhone() + newLine);
        response.append("Cashier: " + getCashier() + newLine);
        response.append("Date: " + getOrderDate().toLocalDate() + newLine);
        response.append("Time: " + getOrderDate().toLocalTime() + newLine);

        response.append("Qty " + tab + "Name" + tab + "Price" + tab + "Total" + newLine);
        for (ProductDto product : getProductsList()) {
            response.append(product.toString() + newLine);
        }
        response.append("Total: " + getTotalPrice());
        response.append(newLine + "Discount Card: " + getDiscountCard());
        return response.toString();
    }
}
