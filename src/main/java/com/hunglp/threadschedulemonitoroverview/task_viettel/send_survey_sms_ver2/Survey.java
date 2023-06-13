package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;

public class Survey {

    private int id;

    private String suspectPhone;

    private String surveyPhone;

    private String timeSpam;

    public Survey() {
    }

    public Survey(int id, String suspectPhone, String surveyPhone, String timeSpam) {
        this.id = id;
        this.suspectPhone = suspectPhone;
        this.surveyPhone = surveyPhone;
        this.timeSpam = timeSpam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuspectPhone() {
        return suspectPhone;
    }

    public void setSuspectPhone(String suspectPhone) {
        this.suspectPhone = suspectPhone;
    }

    public String getSurveyPhone() {
        return surveyPhone;
    }

    public void setSurveyPhone(String surveyPhone) {
        this.surveyPhone = surveyPhone;
    }

    public String getTimeSpam() {
        return timeSpam;
    }

    public void setTimeSpam(String timeSpam) {
        this.timeSpam = timeSpam;
    }
}
