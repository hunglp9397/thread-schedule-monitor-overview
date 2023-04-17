package com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RestartClient {
    @Scheduled(cron = "15 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        System.out.println("Job every 1 minute");
    }
}
