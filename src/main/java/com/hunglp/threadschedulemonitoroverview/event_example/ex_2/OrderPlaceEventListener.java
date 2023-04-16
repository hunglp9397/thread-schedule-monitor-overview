package com.hunglp.threadschedulemonitoroverview.event_example.ex_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(1)
@Component

public class OrderPlaceEventListener  implements ApplicationListener<OrderPlaceEvent> {

    @Autowired
    private EmailService emailService;
    @Override
    @Async
    public void onApplicationEvent(OrderPlaceEvent event) {
        //Send email conffirm to the cusstomer
        Order order = event.getOrder();
        String email = order.getCustomer().getEmail();
        String message = "Thank you for placing an order. Your order ID is " + order.getOrderId();
        emailService.sendMail(email,"Order Confirmation", message);


    }
}
