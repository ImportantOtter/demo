package com.example.demo.util;

import com.example.demo.dto.ChequeDto;
import com.example.demo.dto.ProductDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ChequePrinter {

    public static String printHtmlResponse(ChequeDto parse) {
        String newLine = " </br>";

        StringBuilder response = new StringBuilder();
        response.append("<h3> <========================> <h3>");
        response.append("<h3> CASH RECEIPT </h3>"); //
        response.append("Adress: " + parse.getAddress() + newLine);

        response.append("Number: " + parse.getPhone() + newLine);
        response.append("Cashier: " + parse.getCashier() + newLine);
        response.append("Date: " + parse.getOrderDate().toLocalDate() + newLine);
        response.append("Time: " + parse.getOrderDate().toLocalTime() + newLine);

        response.append("Qty &nbsp; Name &nbsp; Price &nbsp; Total" + newLine);
        for (ProductDto product : parse.getProductsList()) {
            response.append(product.toHtmlString() + newLine);
        }
        response.append("<h2> Total: " + "<b>" +parse.getTotalPrice() + "</b> </h2>");
        response.append("Discount Card: " + parse.getDiscountCard());
        return response.toString();
    }


    public static ResponseEntity<Resource> writeToTheFile(ChequeDto cheque) {
        byte[] array = cheque.toString().getBytes();

        ByteArrayResource resource = new ByteArrayResource(array);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("productList")
                                .build().toString())
                .body(resource);
    }

    public static String printChequeLikeString(ChequeDto cheque){
        return cheque.toString();
    }
}
