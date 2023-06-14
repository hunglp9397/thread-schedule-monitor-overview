package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApp {

    @Autowired
    SurveyController surveyController;


    @Bean
    CommandLineRunner run() {
       surveyController.sendSurvey();
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2.MainApp.class, args);
    }
}
