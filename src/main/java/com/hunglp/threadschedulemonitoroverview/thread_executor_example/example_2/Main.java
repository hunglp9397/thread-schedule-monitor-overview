package com.hunglp.threadschedulemonitoroverview.thread_executor_example.example_2;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//  Dung Thread executor thuc thi vong lap
public class Main {


    public static void main(String[] args) {
        Map<Integer, List<String>> stateNumCitiesMap = new LinkedHashMap<>();

        for(int i = 0 ;i < 100; i ++){
            stateNumCitiesMap.put(i, Arrays.asList("A", "B", "C", "D", "E"));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(Integer key : stateNumCitiesMap.keySet()){
            executorService.execute(() -> writeCitiesOfStateToFile(key, stateNumCitiesMap.get(key)));
        }

        executorService.shutdown();

    }
    public  static void writeCitiesOfStateToFile(int stateNum, List<String> citiesList){
        for(String city : citiesList){
            System.out.println(stateNum + " " + Thread.currentThread().getName());
        }
    }
}