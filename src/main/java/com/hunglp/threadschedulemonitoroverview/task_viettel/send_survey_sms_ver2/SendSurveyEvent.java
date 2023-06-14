package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;

import org.springframework.context.ApplicationEvent;

import java.util.List;



public class SendSurveyEvent extends ApplicationEvent {

    private final List<Survey> surveys;

    public SendSurveyEvent(Object source, List<Survey> surveys) {
        super(source);
        this.surveys = surveys;
    }

    public List<Survey> getSurvey() {
        return surveys;
    }
}
