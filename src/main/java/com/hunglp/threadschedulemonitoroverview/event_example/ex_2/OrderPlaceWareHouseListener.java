package com.hunglp.threadschedulemonitoroverview.event_example.ex_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@org.springframework.core.annotation.Order(2)
public class OrderPlaceWareHouseListener implements ApplicationListener<OrderPlaceEvent> {

    @Autowired
    private WareHouseService wareHouseService;


    @Override
    @Async
    public void onApplicationEvent(OrderPlaceEvent event) {

        // Notify warehouse to prepare order for shipping
        Order order = event.getOrder();
        wareHouseService.prepareForShipping(order.getCustomer().getAddress(), order.getCustomer().getPhoneNumber());

    }
}
