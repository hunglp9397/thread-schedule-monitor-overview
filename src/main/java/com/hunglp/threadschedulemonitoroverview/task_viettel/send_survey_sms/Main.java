package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Survey> surveyList = new ArrayList<>();
        for(int i = 0; i < 100000; i++){
            surveyList.add(new Survey("Suspect"+ i, "Survey" + i, "2023-03-03"));
        }


        // Do Send SMS
        sendSurvey(surveyList);

    }

    public static void sendSurvey(List<Survey> surveyList){


        ;
    }
}
