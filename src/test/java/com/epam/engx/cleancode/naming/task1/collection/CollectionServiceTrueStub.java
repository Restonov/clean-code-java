package com.epam.engx.cleancode.naming.task1.collection;

import com.epam.engx.cleancode.naming.task1.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Order;

class CollectionServiceTrueStub implements CollectionService {
    @Override
    public boolean isEligibleForCollection(Order order) {
        return true;
    }
}
