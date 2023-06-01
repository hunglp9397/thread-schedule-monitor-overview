package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms;

import lombok.Data;

@Data
public class SurveySmsDetailDTO {

    private String suspectPhone;

    private String surveyPhone;

    private boolean isSuccess;

    private String timeSpam;

}
