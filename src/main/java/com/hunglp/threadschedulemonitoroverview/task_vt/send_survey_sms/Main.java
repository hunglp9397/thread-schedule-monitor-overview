package com.hunglp.threadschedulemonitoroverview.task_vt.send_survey_sms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {

        List<Survey> surveyList = new ArrayList<>();
        for(int i = 0; i < 100; i++){
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
        List<Future<List<SurveySmsDetailDTO>>> futures = new ArrayList<>();

        Future<List<SurveySmsDetailDTO>> future;

        List<SendSmsCallable> sendSmsCallableList = new ArrayList<>();

        // Add Callable
        for (int i = 0; i < listSurveySubparts.size(); i++) {
            SendSmsCallable sendSmsCallable = new SendSmsCallable(listSurveySubparts.get(i), new SmsService());
            sendSmsCallableList.add(sendSmsCallable);
        }

        System.out.println("Submitting jobs");

        try {
            futures = executor.invokeAll(sendSmsCallableList);
        } catch (InterruptedException e) {
            System.out.println("ERROR invoke executor Callable " + e.getMessage());
            throw new RuntimeException(e);
        }

        System.out.println("Done jobs");

        executor.shutdown();

        System.out.println("Shutdown jobs");

        System.out.println("Parsing result");
        List<SurveySmsDetailDTO> surveySmsDetailDTOList = new ArrayList<>();
        for (int i = 0; i < futures.size(); i++) {
            try {
                List<SurveySmsDetailDTO> surveyResult = futures.get(i).get();
                surveySmsDetailDTOList.addAll(surveyResult);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error When Load Result! Exception :" + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        System.out.println("---------------------------FINALLY-----------------------------------");
        System.out.println("Result:");
        System.out.println(surveySmsDetailDTOList);
    }
}
