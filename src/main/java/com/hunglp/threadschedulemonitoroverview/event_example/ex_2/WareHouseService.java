package com.hunglp.threadschedulemonitoroverview.event_example.ex_2;

import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class WareHouseService {
    public void prepareForShipping(String address, String phoneNumber){
        System.out.println("Preparing for ship to address : " + address + "with customer's phone number: " + phoneNumber);
    }

}
