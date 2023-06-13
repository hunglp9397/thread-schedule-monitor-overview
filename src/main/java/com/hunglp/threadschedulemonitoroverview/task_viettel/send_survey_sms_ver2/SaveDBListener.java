package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveDBListener implements ApplicationListener<SendSurveyEvent> {


    @Override
    public void onApplicationEvent(SendSurveyEvent event) {
        System.out.println("Listening event...");
        System.out.println("Saving DB...");
        List<Survey> surveys = event.getSurvey();




    }
}
