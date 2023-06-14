package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;


import com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms.SendSmsCallable;
import com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms.SmsService;
import com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms.SurveySmsDetailDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class SurveyServiceTask {

    public void doTaskByActionType(String actionType, List<Survey> surveys){

        // Divide list survey to subList
        int numThreads = 5;
        int nElementsEachThread = surveys.size() / numThreads;
        List<List<Survey>> listSurveySubparts = new ArrayList<>(numThreads);
        for (int i = 0; i < numThreads - 1; i++) {
            listSurveySubparts.add(surveys.subList(i * nElementsEachThread, (i + 1) * nElementsEachThread));
        }
        listSurveySubparts.add(surveys.subList((numThreads - 1) * nElementsEachThread, surveys.size()));


        // Init executor
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        ArrayList<Future<List<SurveySmsDetailDTO>>> futures = new ArrayList<>();

        Future<List<SurveySmsDetailDTO>> future;

        List<SendSmsCallable> sendSmsCallableList = new ArrayList<>();

//        for (int i = 0; i < listSurveySubparts.size(); i++) {
//            SendSmsCallable sendSmsCallable = new SendSmsCallable(listSurveySubparts.get(i), new SmsService());
//            sendSmsCallableList.add(sendSmsCallable);
//        }




    }

    public void saveDB(List<Survey> surveys){
        System.out.println("Save DB");
    }

    public void sendSms(List<Survey> surveys){
        System.out.println("send sms");

    }
}
