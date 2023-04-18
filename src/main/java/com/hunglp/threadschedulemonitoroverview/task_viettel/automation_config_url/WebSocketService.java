package com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    public void connect(String url) throws InterruptedException {
        logger.info("Connectting...", url);
        Thread.sleep(1000);
        logger.info("Decoding...");
    }

    public void close(String url){
        logger.info("Disconnected ws !", url);
    }

}
