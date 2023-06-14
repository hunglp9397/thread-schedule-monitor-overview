package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveDBListener implements ApplicationListener<SendSurveyEvent> {

    @Autowired
    private SurveyServiceTask surveyServiceTask;

    @Override
    public void onApplicationEvent(SendSurveyEvent event) {
        System.out.println("Listening event...");
        List<Survey> surveys = event.getSurvey();
        surveyServiceTask.saveDB(surveys);
    }
}
