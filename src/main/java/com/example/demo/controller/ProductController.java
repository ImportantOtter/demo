package com.example.demo.controller;

import com.example.demo.dto.ChequeDto;
import com.example.demo.service.ProductService;
import com.example.demo.service.impl.OrderParser;
import com.example.demo.util.ChequePrinter;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final String ORDER_EXAMPLE = "3-1 2-5 5-1 card-1";

    private ProductService productService;
    private OrderParser orderParser;

    public ProductController(ProductService productService, OrderParser orderParser) {
        this.productService = productService;
        this.orderParser = orderParser;
    }

    @PostMapping("/order/create")
    public String createOrder(@RequestBody String order) {
        ChequeDto parse = this.orderParser.parse(order);
        String stringCheque = ChequePrinter.printChequeLikeString(parse);
        System.out.println(stringCheque);
        return stringCheque;
    }

    @PostMapping("/order/downloadToFile")
    public ResponseEntity<Resource> downloadOrderToFile(@RequestBody String order) {
        ChequeDto parse = this.orderParser.parse(order);
        return ChequePrinter.writeToTheFile(parse);
    }

    @PostMapping("/order/HtmlPrint")
    public String createOrderLikeHtml(@RequestBody String order) {
        ChequeDto parse = this.orderParser.parse(order);
        String htmlStringCheque = ChequePrinter.printHtmlResponse(parse);
        System.out.println(htmlStringCheque);
        return htmlStringCheque;
    }

    @GetMapping("/exampleFile")
    public ResponseEntity<Resource> printExampleChequeToFile() {
        return downloadOrderToFile(ORDER_EXAMPLE);
    }

    @GetMapping("/exampleHtmlCheque")
    public String printExampleHtmlcheque() {
        return createOrderLikeHtml(ORDER_EXAMPLE);
    }

    @GetMapping("/exampleSimpleCheque")
    public String printExampleCheque() {
        return createOrder(ORDER_EXAMPLE);
    }
}
