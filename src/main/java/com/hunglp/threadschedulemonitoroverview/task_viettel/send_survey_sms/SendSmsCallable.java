package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class SendSmsCallable implements Callable<List<SurveySmsDetailDTO>> {

    SmsService smsService;

    private final List<Survey> surveyList;

    public SendSmsCallable(List<Survey> surveyList,SmsService smsService){
        this.surveyList = surveyList;
        this.smsService = smsService;
    }
    @Override
    public List<SurveySmsDetailDTO> call(){
        List<SurveySmsDetailDTO> surveySmsDetailDTOS = new ArrayList<>();

        surveyList.parallelStream().collect(Collectors.toList()).forEach(survey -> {
            SurveySmsDetailDTO surveySmsDetailDTO = new SurveySmsDetailDTO();
            surveySmsDetailDTO.setSurveyPhone(survey.getSurveyPhone());
            surveySmsDetailDTO.setSuspectPhone(survey.getSuspectPhone());
            surveySmsDetailDTO.setTimeSpam(survey.getTimeSpam());
            surveySmsDetailDTO.setSuccess(true);// fake sent ok
            smsService.sendSms(survey.getSurveyPhone());

            surveySmsDetailDTOS.add(surveySmsDetailDTO);
        });
        return surveySmsDetailDTOS;
    }
}
