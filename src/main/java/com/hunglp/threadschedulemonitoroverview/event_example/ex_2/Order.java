package com.hunglp.threadschedulemonitoroverview.event_example.ex_2;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Order {

    private Long orderId;

    private Date orderAt;

    private Customer customer;


}
