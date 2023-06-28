package com.hunglp.threadschedulemonitoroverview.task_vt.send_survey_sms_ver2;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class SurveyTaskCallable implements Callable<Map<Integer, String>> {

    private List<Survey> surveys;

    private String actionType;

    SurveyTaskCallable(List<Survey> surveys, String actionType){
        this.surveys = surveys;
        this.actionType = actionType;
    }

    @Override
    public Map<Integer, String> call()  {

        Map<Integer, String> mapResults = new HashMap<>();

        if(actionType.equals("savedb")){

        } else if(actionType.equals("sendsms")){

        }

        return mapResults;

    }
}
