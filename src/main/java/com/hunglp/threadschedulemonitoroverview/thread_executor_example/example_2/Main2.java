package com.hunglp.threadschedulemonitoroverview.thread_executor_example.example_2;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, List<String>> stateNumCitiesMap = new LinkedHashMap<>();

        for(int i = 0;i < 100;i++){
            stateNumCitiesMap.put(i, Arrays.asList("A", "B", "C", "D"));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<Void>> jobs = new ArrayList<>();
        for(Integer key : stateNumCitiesMap.keySet()){
            jobs.add( () -> {
                writeCitiesOfStateToFile(key, stateNumCitiesMap.get(key));
                return null;
            });
        }

        executorService.invokeAll(jobs);
        executorService.shutdown();
    }

    public static void writeCitiesOfStateToFile(Integer stateNum, List<String> citiesOfState){
        for(String city: citiesOfState){
            System.out.println(stateNum + "__" + city  + "__" + Thread.currentThread().getName() );
        }
    }


}
