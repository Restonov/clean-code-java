package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;
import lombok.Data;

import java.util.List;

@Data
public class Order {

    private List<Product> products;

    public Double retrievePriceOfAvailableProducts() {
        return products.stream()
                .filter(Product::isAvailable)
                .map(Product::getProductPrice)
                .mapToDouble(p -> p)
                .sum();
    }
}
