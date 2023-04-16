package com.hunglp.threadschedulemonitoroverview.event_example.ex_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApp {

    @Autowired
    MyHouse myHouse;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }


    @Bean
    CommandLineRunner run() {
        return args -> {
            System.out.println(Thread.currentThread().getName() + ": Loda đi tới cửa nhà !!!");
            System.out.println(Thread.currentThread().getName() + ": => Loda bấm chuông và khai báo họ tên!");
            // gõ cửa
            myHouse.rangDoorbellBy("Loda");
            System.out.println(Thread.currentThread().getName() +": Loda quay lưng bỏ đi");
        };
    }


}
