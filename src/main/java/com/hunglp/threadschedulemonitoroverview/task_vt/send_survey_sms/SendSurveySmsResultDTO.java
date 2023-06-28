package com.hunglp.threadschedulemonitoroverview.task_vt.send_survey_sms;

import lombok.Data;

import java.util.List;

@Data
public class SendSurveySmsResultDTO {

    private Long totalSuccess;

    private Long totalFail;

    private List<SurveySmsDetailDTO> surveySmsDetailList;
}
