package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;


import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class SurveyTaskCallable implements Callable<Map<Integer, String>> {

    private List<Survey> surveys;

    SurveyTaskCallable(List<Survey> surveys){
        this.surveys = surveys;
    }

    @Override
    public Map<Integer, String> call()  {
        return null;
    }
}
