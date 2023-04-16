package com.hunglp.threadschedulemonitoroverview.event_example.ex_2;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class OrderPlaceEvent extends ApplicationEvent {

    private final Order order;

    public  OrderPlaceEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
