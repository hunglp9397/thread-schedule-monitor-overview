package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        long startTime = System.currentTimeMillis();

        int numThreads = 5;


        // Divide list survey to subList
        int nElementsEachThread = surveyList.size() / numThreads;
        List<List<Survey>> listSurveySubparts = new ArrayList<>(numThreads);
        for (int i = 0; i < numThreads - 1; i++) {
            listSurveySubparts.add(surveyList.subList(i * nElementsEachThread, (i + 1) * nElementsEachThread));
        }
        listSurveySubparts.add(surveyList.subList((numThreads - 1) * nElementsEachThread, surveyList.size()));
        System.out.println("Divided into " + numThreads + " parts");



        // Init executor
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        ArrayList<Future<List<SurveySmsDetailDTO>>> futures = new ArrayList<>();

        Future<List<SurveySmsDetailDTO>> future;

        List<SendSmsCallable> sendSmsCallableList = new ArrayList<>();

        for (int i = 0; i < listSurveySubparts.size(); i++) {
            SendSmsCallable sendSmsCallable = new SendSmsCallable(listSurveySubparts.get(i), new SmsService());
            sendSmsCallableList.add(sendSmsCallable);
        }

    }
}
