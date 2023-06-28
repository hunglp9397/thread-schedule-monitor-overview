package com.hunglp.threadschedulemonitoroverview.task_vt.send_survey_sms_ver2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void sendSurvey(List<Survey> surveyList){
        eventPublisher.publishEvent(new SendSurveyEvent(this,surveyList));
    }


}
