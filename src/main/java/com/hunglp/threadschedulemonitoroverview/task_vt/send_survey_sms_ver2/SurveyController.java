package com.hunglp.threadschedulemonitoroverview.task_vt.send_survey_sms_ver2;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/send")
    public void sendSurvey(){
        List<Survey> surveys = new ArrayList<>();
        surveys.add(new Survey(1, "A", "B", "1"));
        surveys.add(new Survey(2, "C", "D", "2"));
        surveys.add(new Survey(3, "E", "F", "3"));
        surveys.add(new Survey(4, "G", "H", "4"));
        surveys.add(new Survey(5, "I", "K", "5"));

        surveyService.sendSurvey(surveys);
    }


}
