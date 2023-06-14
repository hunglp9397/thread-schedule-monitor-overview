package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;


import com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms.SendSmsCallable;
import com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms.SurveySmsDetailDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class SurveyServiceTask {
    public void doTaskByActionType(String actionType, List<Survey> surveys) {

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
        List<Future<Map<Integer,String>>> futures = new ArrayList<>();

        Future<Map<Integer,String>> future;
        List<SurveyTaskCallable> surveyTaskCallables = new ArrayList<>();

        for (int i = 0; i < listSurveySubparts.size(); i++) {
            SurveyTaskCallable surveyTaskCallable = new SurveyTaskCallable(listSurveySubparts.get(i), actionType);
            surveyTaskCallables.add(surveyTaskCallable);
        }

        System.out.println("Submitting jobs");

        try {
            futures = executor.invokeAll(surveyTaskCallables);
        } catch (InterruptedException e) {
            System.out.println("ERROR invoke executor Callable " + e.getMessage());
            throw new RuntimeException(e);
        }

        System.out.println("Done jobs");

        executor.shutdown();

        System.out.println("Shutdown jobs");

        System.out.println("Parsing result");


        // GET RESULT
        List<Map<Integer,String>> mapResults = new ArrayList<>();
        for (int i = 0; i < futures.size(); i++) {
            try {
                Map<Integer,String> map = futures.get(i).get();
                mapResults.add(map);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error When Load Result! Exception :" + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        System.out.println("------------------------------------------FINALY-----------------------------------------");
        System.out.println("Result:");
        System.out.println(mapResults);
    }
}
