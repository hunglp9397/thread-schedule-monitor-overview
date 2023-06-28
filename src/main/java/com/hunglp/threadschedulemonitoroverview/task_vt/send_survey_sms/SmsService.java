package com.hunglp.threadschedulemonitoroverview.task_vt.send_survey_sms;

public class SmsService {

    public void sendSms(String surveyPhone){
        System.out.println("Sending sms to surveyPhone....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sent");
    }
}
