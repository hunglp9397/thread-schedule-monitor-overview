package com.hunglp.threadschedulemonitoroverview.task_vt.automation_config_url;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebSocketService {

    private final String url;

    Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    public WebSocketService(String url) {

        this.url = url;
    }

    public boolean connect() throws InterruptedException {
        logger.info("Connecting... : {}", this.url);
        Thread.sleep(1000);
        logger.info("Decoding...");
        Thread.sleep(500);
        logger.info("Decode done!");
        return true;
    }

    public void close(){
        logger.info("Disconnected websocket. URL: {}", this.url);
    }



}
