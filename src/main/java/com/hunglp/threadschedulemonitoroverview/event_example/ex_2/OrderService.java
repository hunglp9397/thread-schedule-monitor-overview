package com.hunglp.threadschedulemonitoroverview.event_example.ex_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void placeOrder(Order order){
        //Save the order to the database


        //Publish order event
        eventPublisher.publishEvent(new OrderPlaceEvent(this,order));
    }
}

