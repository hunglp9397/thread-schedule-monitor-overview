package com.hunglp.threadschedulemonitoroverview.task_vt.send_survey_sms;

 class Survey {

    private String suspectPhone;

    private String surveyPhone;

    private String timeSpam;

    public Survey() {
    }

    public Survey(String suspectPhone, String surveyPhone, String timeSpam) {
        this.suspectPhone = suspectPhone;
        this.surveyPhone = surveyPhone;
        this.timeSpam = timeSpam;
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
