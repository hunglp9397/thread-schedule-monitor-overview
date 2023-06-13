package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;


import com.hunglp.threadschedulemonitoroverview.event_example.ex_1.MainApp;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/survey")
public class SurveyController {
    @GetMapping("/send")
    public void sendSurvey(){
        System.out.println("OK");
    }


}
