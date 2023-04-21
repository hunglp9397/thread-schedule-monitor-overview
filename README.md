# Ví dụ về thread, multithread, monitor, schedule (Bao gồm cac task ở Viettel)

##### - Ở mỗi package có file package-info miêu tả require example hoặc cách implement, giải thích :D\\

===========EvenListenConfig======
@Configuration
@EnableAsync
public class EventListenConfig {

    /**
     * Setup default task executor for asynchronous event listener
     * @return: TaskExecutor instance
     */
    @Bean
    TaskExecutor taskExecutor() {
        // SimpleAsyncTaskExecutor always generate a new thread for each incoming requests
        return new SimpleAsyncTaskExecutor();

        // ConcurrentTaskExecutor limits the number of concurrent threads in the maybe fixed pool
        // return new ConcurrentTaskExecutor();

        /*
        // ThreadPoolTaskExecutor defines almost things like number-based concurrent threads
        // maximum concurrent ones, or maximum queue size for requests that exceeded based-concurrent
        // It will ignore new requests when both the pool of threads and waiting list in queue are full

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("CustomizedThreadPoolTaskExecutor-");
        taskExecutor.setCorePoolSize(100);
        taskExecutor.setMaxPoolSize(5000);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.initialize();
        return taskExecutor;
         */
    }
}


==========================

=====VoiceToTextBatchEvent==========
public class CallV2TBatchEvent extends ApplicationEvent {
    private final CallBatch callBatch;

    public CallV2TBatchEvent(Object source, CallBatch callBatch) {
        super(source);
        this.callBatch = callBatch;
    }

    public CallBatch getCallBatch() {
        return callBatch;
    }
}

====================================


=== VoiceToTextBatchPublisher====

@Service
public class VoiceToTextBatchPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public VoiceToTextBatchPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishCallBatchEvent(CallBatch callBatch) {
        applicationEventPublisher.publishEvent(new CallV2TBatchEvent(this, callBatch));
    }
}


=====================================




==========VoiceToTexSubcriber=====
@Service
public class VoiceToTexSubcriber {
  @Async
    @EventListener
    public void DoSomething(VoiceToTextBatchEvent voiceToTextBatchEvent) {
    
    
    }
}

=====

=================================




=============Web Socket Client========

public class WebSocketClient implements Closeable {

    public void start(){
    }
    
    public void stop(){
    }
}










