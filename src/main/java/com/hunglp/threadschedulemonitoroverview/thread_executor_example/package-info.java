package com.hunglp.threadschedulemonitoroverview.thread_executor_example;

/*
    1. ThreadPoolExecutor là một class nâng cao hơn của ThreadPool
    - Đặc điểm của ThreadPool là k đủ linh động, điển hình là số lượng thread bị fix
    - Còn ThreadPoolExecutor là một phiên bản nâng ấp, cho phép tùy biến số lượng Thread theo kịch bản
    - ThreadPoolExecutor và ThreadPoolTaskExecutor cũng là Executor nhưng có thêm tham số sau :
       + corePoolSize (Số lượng Thread mặc định trong Pool)
       + maxPoolSize: Số lượng tối đa Thread trong Pool
       + queueCapacity: Số lượng tối da của BlockingQueue

* */