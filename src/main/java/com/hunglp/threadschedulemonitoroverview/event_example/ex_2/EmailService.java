package com.hunglp.threadschedulemonitoroverview.event_example.ex_2;

import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendMail(String email, String title, String message){
        System.out.println("Send to email: " + email+ " with title" + title + " with content:" + message);
    }
}
