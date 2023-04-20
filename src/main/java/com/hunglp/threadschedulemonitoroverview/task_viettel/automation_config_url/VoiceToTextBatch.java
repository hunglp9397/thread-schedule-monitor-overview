package com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class VoiceToTextBatch {

    Logger logger = LoggerFactory.getLogger(VoiceToTextBatch.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private List<String> listOfUrlsWebSocket;

    AtomicInteger urlV2TIndex = new AtomicInteger(1);


    // Map url -> status
    private ConcurrentHashMap<String, String> urlToStatus;

    // Map url -> List client that connected
    private ConcurrentHashMap<String, List<WebSocketService>> urlToInstance;

    @Autowired
    public VoiceToTextBatch(@Value("#{'${listOfUrlsWebSocket}'.split(',')}") List<String> listOfUrlsWebSocket) {
        this.listOfUrlsWebSocket = listOfUrlsWebSocket;

        // Init Map url -> status. Default all url is OK
        urlToStatus = new ConcurrentHashMap<>();

        // Init map url -> List client active
        urlToInstance = new ConcurrentHashMap<>();

        // Init default value for 2 map
        listOfUrlsWebSocket.forEach(item -> {
            urlToStatus.put(item, WebSocketStatus.OK);
            urlToInstance.put(item, new ArrayList<>());
        });

    }


    public void executeDecodeVoiceToText(){

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                logger.info("---START---");

                // Get url
                String url = getUrlWebSocket();

                // Init ws, then connect
                WebSocketService ws = new WebSocketService(url);
                boolean response = false;
                try {
                    response = ws.connect();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(response){
                    urlToInstance.compute(url, (k, v) -> {
                        v.add(ws);
                        return v;
                    });

                    // Suppose Decoding as Thread.sleep
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // Close websocket
                ws.close();


                // Remove instance from list
                urlToInstance.computeIfPresent(url, (k,v)->{
                    v.remove(ws);
                    return v;
                });

                logger.info("----END---- \n");
            });
        }





    }

    private String getUrlWebSocket() {
        List<String> urlsAvailable = urlToInstance.entrySet().stream()
                .filter(e -> urlToStatus.containsValue(WebSocketStatus.OK))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (urlsAvailable.isEmpty()) {
            logger.info("There are no url available server for connect");
            return null;
        }

        return urlsAvailable.get(urlV2TIndex.getAndIncrement() % urlsAvailable.size());

    }


}
