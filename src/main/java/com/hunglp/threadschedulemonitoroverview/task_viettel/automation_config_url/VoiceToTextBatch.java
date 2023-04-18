package com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class VoiceToTextBatch {


    private List<String> listOfUrlsWebSocket;


    // Map url -> status
    private ConcurrentHashMap<String,String> urlToStatus;

    // Map url -> List client that connected
    private ConcurrentHashMap<String,List<VoiceToTextBatch>> urlToInstance;
    @Autowired
    public VoiceToTextBatch( @Value("#{'${listOfUrlsWebSocket}'.split(',')}")  List<String> listOfUrlsWebSocket){
        this.listOfUrlsWebSocket = listOfUrlsWebSocket;

        // Init Map url -> status. Default all url is OK
        urlToStatus = new ConcurrentHashMap<>();

        // Init map url -> List client active
        urlToInstance = new ConcurrentHashMap<>();

        // Init default value for 2 map
        listOfUrlsWebSocket.forEach(item ->{
            urlToStatus.put(item, WebSocketStatus.OK);
            urlToInstance.put(item, new ArrayList<>());
        });

    }


    public void executeDecodeVoiceToText(){

        // Get url

        List<String> urlsAvailable = urlToInstance.entrySet().stream()
                .filter(e -> urlToStatus.containsValue(WebSocketStatus.OK))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(urlsAvailable);



        // Connect WebSocket

        // Close websocket
    }


}
