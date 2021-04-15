package com.epam.engx.cleancode.naming.task1.impl;

import com.epam.engx.cleancode.naming.task1.OrderService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.DeliveryService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.NotDeliverableOrderException;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.OrderFulfilmentService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryService implements OrderService {

    private DeliveryService deliveryService;
    private OrderFulfilmentService orderFulfilmentService;

    public void submit(Order order) {
        if (deliveryService.isDeliverable(order)) {
            List<Product> products = order.getProducts();
            orderFulfilmentService.fulfilProducts(products);
        } else {
            throw new NotDeliverableOrderException();
        }
    }
}
