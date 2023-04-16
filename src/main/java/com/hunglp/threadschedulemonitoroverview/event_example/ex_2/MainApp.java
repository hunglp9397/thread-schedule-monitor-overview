package com.hunglp.threadschedulemonitoroverview.event_example.ex_2;

import com.hunglp.threadschedulemonitoroverview.event_example.ex_1.MyHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MainApp {

    @Autowired
    OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(com.hunglp.threadschedulemonitoroverview.event_example.ex_2.MainApp.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            Customer customer = new Customer();
            customer.setName("Hung");
            customer.setEmail("hunglp9@gmail.com.vn");
            customer.setPhoneNumber("0934642619");
            customer.setAddress("Ha Nam Viet Nam");

            Order order = new Order();
            order.setOrderId(1L);
            order.setOrderAt(new Date());
            order.setCustomer(customer);

            orderService.placeOrder(order);


        };
    }
}
