package com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@EnableScheduling
@SpringBootApplication
public class MainApp {

    @Autowired
    private WebSocket webSocket;

    public static void main(String[] args) {
        SpringApplication.run(com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url.MainApp.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            webSocket.connectWebSocket();


        };
    }
}


