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


- Task thứ hai về Thread:

Module "anti-business-call" cần send thông tin payload về survey sang module "sms-api"


Ta cần gửi 1 list các susrvey, Mỗi survey như sau : suspectPhone, surveyPhone, daDate

Luồng sms-api cần phải thực hiện gửi bản tin và insert thông tin vô bảng survey

=> Giải quyết ta dùng Excutor Service cho con sms_api

ExecutorService executor = Executors.newFixedThreadPool(numThreads);


// Dùng thằng Future này để callable trả về được kết quả json String

List<Future<List<SurveySmsDetailDTO>>> futures = new ArrayList<>();
        Future<List<SurveySmsDetailDTO>> future;

        List<SendSmsCallable> sendSmsCallableList = new ArrayList<>();

        for (int i = 0; i < listSurveySubparts.size(); i++) {
            SendSmsCallable sendSmsCallable = new SendSmsCallable(listSurveySubparts.get(i), surveyRepository, sendSmsService);
            sendSmsCallableList.add(sendSmsCallable);
        }

        LOGGER.info("Submitting jobs");

        try {
            futures = executor.invokeAll(sendSmsCallableList);
        } catch (InterruptedException e) {
            LOGGER.error("ERROR invoke executor Callable " + e.getMessage());
            throw new RuntimeException(e);
        }

        
        // SendSmsCallable implement callable, Thực hiện send và save thong tin khảo sát
        
public class SendSmsCallable implements Callable<List<SurveySmsDetailDTO>> {

    private static final Logger LOGGER = Logger.getLogger(SendSmsCallable.class);
    List<Survey> surveyList;

    SurveyRepository surveyRepository;

    SendSmsService sendSmsService;


    public SendSmsCallable(List<Survey> surveyList, SurveyRepository surveyRepository, SendSmsService sendSmsService ) {
        this.surveyList = surveyList;
        this.surveyRepository = surveyRepository;
        this.sendSmsService = sendSmsService;
    }

    @Override
    public List<SurveySmsDetailDTO> call() {
        return getListSurveySent(surveyList);
    }

    public  List<SurveySmsDetailDTO> getListSurveySent(List<Survey> surveyList) {

        List<SurveySmsDetailDTO> surveySmsDetailDTOS = new ArrayList<>();

        surveyList.parallelStream().forEach(survey -> {
            SurveySmsDetailDTO surveySmsDetailDTO = new SurveySmsDetailDTO();
            surveySmsDetailDTO.setSurveyPhone(survey.getSurveyPhone());
            surveySmsDetailDTO.setSuspectPhone(survey.getSpamPhone());
            surveySmsDetailDTO.setdDate(survey.getdDate());
            surveySmsDetailDTO.setTimeSpam(survey.getTimeSpam());

            long startTime = System.currentTimeMillis();
            if(surveyRepository.save(survey) == 1 && sendSmsService.sendSms(survey) == 1){
                surveySmsDetailDTO.setSuccess(true);
            }else{
                surveySmsDetailDTO.setSuccess(false);
            }
            surveySmsDetailDTO.setLatencyMs(System.currentTimeMillis() - startTime);
            surveySmsDetailDTOS.add(surveySmsDetailDTO);
        });

        return surveySmsDetailDTOS;
    }
}
==> Như code trên hình, ta dùng thêm parallelSream để tối ưu luồng đọc bản tin, save và send










