package com.epam.engx.cleancode.naming.task1.collection;

import com.epam.engx.cleancode.naming.task1.impl.OrderCollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Submitable;

public class CollectionOrderServiceTestHelper {

    public OrderCollectionService getService(){
        return new OrderCollectionService();
    }

    public void submit(Submitable collectOrderService) {
        ((OrderCollectionService) collectOrderService).submit(new OrderDummy());
    }
}
