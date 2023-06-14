package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceTask {

    public void saveDB(List<Survey> surveys){
        System.out.println("Save DB");
    }

    public void sendSms(List<Survey> surveys){
        System.out.println("send sms");

    }
}
