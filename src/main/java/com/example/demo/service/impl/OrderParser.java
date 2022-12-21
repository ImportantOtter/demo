package com.example.demo.service.impl;

import com.example.demo.dto.ChequeDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.service.DiscountCardService;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrderParser {

    private ProductService productService;
    private DiscountCardService discountCardService;

    public OrderParser(ProductService productService, DiscountCardService discountCardService) {
        this.productService = productService;
        this.discountCardService = discountCardService;
    }

    double totalPrice = 0;
    double discountPrice = 0;
    double discount = 0;
    int discountCount = 0;
    double discountCard = 0;

    public ChequeDto parse(String orderString) {
        ChequeDto chequeDto = new ChequeDto();
        if (orderString.contains("card")) {
            return calculateWithCard(chequeDto, orderString);
        }
        return calculateWithoutCard(chequeDto, orderString);
    }

    private ChequeDto calculateWithCard(ChequeDto chequeDto, String orderString) {
        String[] orders = orderString.split(" ");
        for (int i = 0; i < orders.length - 1; i++) {

            int productId = getProductId(orders[i]);
            ProductDto product = productService.getProductById(productId);
            int quantity = getQuantity(orders[i]);

            double price = product.getPrice();
            String name = product.getName();
            chequeDto.addProduct(new ProductDto(name, price, quantity, product.getPromotionalProduct()));
        }

        for (ProductDto product : chequeDto.getProductsList()) {
            totalPrice += product.getTotalPrice();

            if (isDiscountPresent(product)) {
                discountCount += product.getQuantity();
            }
        }

        int productCardId = getProductCardId(orderString);
        double discountPercent = discountCardService.getDiscountCardById(productCardId).getDiscount();
        if (discountPercent > 0) {
            discountCard = totalPrice * discountPercent / 100;
            totalPrice -= discountCard;
        }

        calculateDiscount(chequeDto);

        chequeDto.setTotalPrice(totalPrice);
        chequeDto.setOrderDate(LocalDateTime.now());
        chequeDto.setId(new Random().nextLong());
        chequeDto.setCashier("Galya");
        chequeDto.setPhone("0-000-00-00");
        chequeDto.setAddress("New-York, st Lyusia 32");
        chequeDto.setDiscountPercentage(0);
        chequeDto.setDiscountCard(discountCard);
        return chequeDto;
    }

    private ChequeDto calculateWithoutCard(ChequeDto chequeDto, String orderString) {
        String[] orders = orderString.split(" ");
        for (String order : orders) {
            int productId = getProductId(order);
            ProductDto product = productService.getProductById(productId);
            int quantity = getQuantity(order);

            double price = product.getPrice();
            String name = product.getName();
            chequeDto.addProduct(new ProductDto(name, price, quantity, product.getPromotionalProduct()));
        }

        for (ProductDto product : chequeDto.getProductsList()) {
            totalPrice += product.getTotalPrice();

            if (isDiscountPresent(product)) {
                discountCount += product.getQuantity();
            }
        }

        calculateDiscount(chequeDto);

        chequeDto.setTotalPrice(totalPrice);
        chequeDto.setOrderDate(LocalDateTime.now());
        chequeDto.setId(new Random().nextLong());
        chequeDto.setCashier("Galya");
        chequeDto.setPhone("0-000-00-00");
        chequeDto.setAddress("New-York, st Lyusia 32");
        chequeDto.setDiscountPercentage(0);
        return chequeDto;
    }

    private void calculateDiscount(ChequeDto chequeDto) {
        for (ProductDto product : chequeDto.getProductsList()) {
            if (discountCount >= 5) {
                if (isDiscountPresent(product)) {
                    discountPrice += product.getTotalPrice();
                }
            }
            discount = discountPrice * 10 / 100;
            totalPrice -= discount;
        }
    }

    private boolean isDiscountPresent(ProductDto product) {
        return product.getPromotionalProduct() && product.getPromotionalProduct() != null;
    }

    private static int getQuantity(String needToParse) {
        String patternAfterHyphen = "(?<=-).*";
        return getFirstGroupByPattern(needToParse, patternAfterHyphen);
    }

    private static int getProductId(String needToParse) {
        String patternBeforeHyphen = ".*(?=-)";
        return getFirstGroupByPattern(needToParse, patternBeforeHyphen);
    }

    private static int getProductCardId(String needToParse) {
        String patternGetCardId = "(\\d+)[^-]*$";
        Pattern compile = Pattern.compile(patternGetCardId);
        Matcher matcher = compile.matcher(needToParse);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(0));
        }
        return 0;
    }

    private static int getFirstGroupByPattern(String parseString, String patternAfterHyphen) {
        Pattern compile = Pattern.compile(patternAfterHyphen);
        Matcher matcher = compile.matcher(parseString);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(0));

            } catch (NumberFormatException e) {

            }
        }
        return 0;
    }
}
