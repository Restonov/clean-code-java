package com.epam.engx.cleancode.naming.task1.collection;

import com.epam.engx.cleancode.naming.task1.impl.OrderCollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Message;
import org.junit.Before;
import org.junit.Test;

import static com.epam.engx.cleancode.naming.task1.impl.OrderCollectionService.CRITICAL_NOTIFICATION_LEVEL;
import static com.epam.engx.cleancode.naming.task1.impl.OrderCollectionService.INFO_NOTIFICATION_LEVEL;
import static org.junit.Assert.assertEquals;

public class OrderCollectionServiceTest {

    private OrderCollectionService orderCollectionService;
    private CollectionServiceTrueStub collectionServiceTrueStub;
    private CollectionServiceFalseStub collectionServiceFalseStub;
    private NotificationManagerMock notificationManagerMock;

    @Before
    public void setUp() throws Exception {
        collectionServiceTrueStub = new CollectionServiceTrueStub();
        collectionServiceFalseStub = new CollectionServiceFalseStub();
        notificationManagerMock = new NotificationManagerMock();
    }

    @Test
    public void shouldSubmitCollectionOrder() {
        orderCollectionService = new OrderCollectionService(collectionServiceTrueStub, notificationManagerMock);
        orderCollectionService.submit(new OrderDummy());
        assertEquals(INFO_NOTIFICATION_LEVEL, notificationManagerMock.level);
        assertEquals(Message.READY_FOR_COLLECT, notificationManagerMock.message);
    }

    @Test
    public void shouldNotSubmitCollectionOrder() {
        orderCollectionService = new OrderCollectionService(collectionServiceFalseStub, notificationManagerMock);
        orderCollectionService.submit(new OrderDummy());
        assertEquals(CRITICAL_NOTIFICATION_LEVEL, notificationManagerMock.level);
        assertEquals(Message.IMPOSSIBLE_TO_COLLECT, notificationManagerMock.message);
    }

}
