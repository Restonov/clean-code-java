package com.epam.engx.cleancode.naming.task1.impl;


import com.epam.engx.cleancode.naming.task1.OrderService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Message;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.NotificationManager;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class OrderCollectionService implements OrderService {
    public static final int INFO_NOTIFICATION_LEVEL = 4;
    public static final int CRITICAL_NOTIFICATION_LEVEL = 1;

    private CollectionService collectionService;
    private NotificationManager notificationManager;

    public void submit(Order order) {
        if (collectionService.isEligibleForCollection(order)) {
            notificationManager.notifyCustomer(Message.READY_FOR_COLLECT, INFO_NOTIFICATION_LEVEL);
        } else {
            notificationManager.notifyCustomer(Message.IMPOSSIBLE_TO_COLLECT, CRITICAL_NOTIFICATION_LEVEL);
        }
    }
}
