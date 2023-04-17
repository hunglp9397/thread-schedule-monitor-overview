package com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSocket{

    @Value("${listOfUrls}")
    private List<String>  listOfUrls;

    public void connectWebSocket(){
        String url = listOfUrls.get(0);
        System.out.println("Connected to webSocket" + url);
    }


}
