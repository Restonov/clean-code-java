package com.epam.engx.cleancode.naming.task1.delivery;

import com.epam.engx.cleancode.naming.task1.impl.OrderDeliveryService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.NotDeliverableOrderException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderDeliveryServiceTest {

    private OrderDeliveryService orderDeliveryService;
    private OrderFulfilmentServiceMock orderFulfilmentServiceMock;
    private DeliveryServiceTrueStub deliveryServiceTrueStub;
    private DeliveryServiceFalseStub deliveryServiceFalseStub;

    @Before
    public void setUp() {
        orderFulfilmentServiceMock = new OrderFulfilmentServiceMock();
        deliveryServiceTrueStub = new DeliveryServiceTrueStub();
        deliveryServiceFalseStub = new DeliveryServiceFalseStub();
    }

    @After
    public void after() {
        orderFulfilmentServiceMock = null;
        deliveryServiceTrueStub = null;
        deliveryServiceFalseStub = null;
    }

    @Test
    public void shouldDeliverProducts() {
        orderDeliveryService = new OrderDeliveryService(deliveryServiceTrueStub, orderFulfilmentServiceMock);
        orderDeliveryService.submit(new OrderStub("product-1"));
        orderFulfilmentServiceMock.assertFirstProductName("product-1");
    }

    @Test(expected = NotDeliverableOrderException.class)
    public void shouldNotDeliverProducts() {
        orderDeliveryService = new OrderDeliveryService(deliveryServiceFalseStub, orderFulfilmentServiceMock);
        orderDeliveryService.submit(new OrderStub("product-1"));
    }
}
