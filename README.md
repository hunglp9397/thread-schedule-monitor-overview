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

=> Giải quyết ta dùng Excutor Service cho con sms_api, 

    @PostMapping(path = "/send", consumes = "application/json", produces = "application/json")
    public String updateResponseVer2(@RequestBody List<Survey> request) {
        long startTime = System.currentTimeMillis();
        String resultStr = null;
        try {
            LOGGER.info(String.format("Received request size =  %d",request.size()));

            int numThreads = 5;


            int nElementsEachThread = request.size() / numThreads;

            List<List<Survey>> listSurveySubparts = new ArrayList<>(numThreads);

            for (int i = 0; i < numThreads - 1; i++) {
                listSurveySubparts.add(request.subList(i * nElementsEachThread, (i + 1) * nElementsEachThread));
            }
            listSurveySubparts.add(request.subList((numThreads - 1) * nElementsEachThread, request.size()));


            LOGGER.info("Divided into " + numThreads + " parts");


            ExecutorService executor = Executors.newFixedThreadPool(numThreads);

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

            LOGGER.info("Done jobs");

            executor.shutdown();

            LOGGER.info("Shutdown jobs");

            LOGGER.info("Parsing result");
            List<SurveySmsDetailDTO> surveySmsDetailDTOList = new ArrayList<>();
            for (int i = 0; i < futures.size(); i++) {
                try {
                    List<SurveySmsDetailDTO> surveyResult = futures.get(i).get();
                    surveySmsDetailDTOList.addAll(surveyResult);
                } catch (InterruptedException | ExecutionException e) {
                    LOGGER.error("Error When Load Result! Exception :" + e.getMessage());
                    throw new RuntimeException(e);
                }
            }

            long totalSuccess = surveySmsDetailDTOList
                    .stream()
                    .filter(SurveySmsDetailDTO::isSuccess)
                    .count();

            long totalFail = surveySmsDetailDTOList
                    .stream()
                    .filter(s -> !s.isSuccess())
                    .count();

            SendSurveySmsResultDTO resultDTO = new SendSurveySmsResultDTO();
            resultDTO.setSurveySmsDetailList(surveySmsDetailDTOList);
            resultDTO.setTotalSuccess(totalSuccess);
            resultDTO.setTotalFail(totalFail);


            try {
                ObjectMapper objectMapper = new ObjectMapper();
                resultStr = objectMapper.writeValueAsString(resultDTO);

            } catch (Exception e) {
                LOGGER.error("Convert Object to JSON Fail!" + e.getMessage());
            }

            LOGGER.info("Parsed result");
            LOGGER.info("TOTAL_SUCCESS: " + resultDTO.getTotalSuccess());
            LOGGER.info("TOTAL_FAIL: " + resultDTO.getTotalFail());
            LOGGER.info("LIST SIZE: " + resultDTO.getSurveySmsDetailList().size());
            LOGGER.info("TIME EXECUTE THIS API : "+ (System.currentTimeMillis() - startTime));
            LOGGER.info("-------------------");

        } catch (Exception e){
            LOGGER.error(String.format("%s", e));
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return resultStr;
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
    
Tuy nhiên ArrayList xử lí đa luồng dẫn đến hiện tượng không đọc hết các phần tử trong một List => Dẫn đến lỗi NullPointerException

Do đó sửa lại đoạn sử dụng paralelStream như sau : 


public  List<SurveySmsDetailDTO> getListSurveySent(List<Survey> surveyList) {

        LOGGER.info("Survey Size Handle : " + surveyList.size());

        List<SurveySmsDetailDTO> surveySmsDetailDTOS = new ArrayList<>();

        try {
            surveyList.parallelStream().collect(Collectors.toList()).forEach(survey -> {
                try {
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
                } catch (Exception e) {
                    LOGGER.error(String.format("%s", e));
                    LOGGER.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            LOGGER.error(String.format("%s", e));
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return surveySmsDetailDTOS;
    }
Blog Hay để vọc code : https://yellowcodebooks.com/category/lap-trinh-java/page/2/
    
Example 1: 
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    
    Giúp tạo ra một ThreadPool có khả năng thực thi 1 Thread trong đó > Các Thread thực thi tuần tự lần lượt
    
Example 2 : 
    
    ExecutorService executorService = Executors.newCachedThreadPool()
    
    => Hệ thống tự quyết định số lượng Thread thực hiện trong ThreadPool

Example 3:
    
    ExecutorService excuctorService = new FixedThreadPool(int nThread)
    
    => Tạo ra một ThreadPool có chứa tối đa nThread, Khi ThreadPool đạt đến giá trị tối đa nThread. Các thread còn lại sẽ đưa vào hàng chờ và đợi đến khi Thread có trong Pool được xử lý rồi mới thực thi tiếp
    




