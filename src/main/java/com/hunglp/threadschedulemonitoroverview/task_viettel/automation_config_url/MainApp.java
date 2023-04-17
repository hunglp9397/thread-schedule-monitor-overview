package com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url;

import com.hunglp.threadschedulemonitoroverview.event_example.ex_2.Customer;
import com.hunglp.threadschedulemonitoroverview.event_example.ex_2.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@EnableScheduling
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url.MainApp.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {

            System.out.println("You will do it");

        };
    }
}


