package com.hunglp.threadschedulemonitoroverview.event_example.ex_1;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;


public class DoorBellEvent extends ApplicationEvent {
    /*
        Mọi Class kế thừa ApplicationEvent sẽ
        phải gọi Constructor tới lớp cha.
     */
    public DoorBellEvent(Object source, String guestName) {
        // Object source là object tham chiếu tới
        // nơi đã phát ra event này!
        super(source);
    }
}
