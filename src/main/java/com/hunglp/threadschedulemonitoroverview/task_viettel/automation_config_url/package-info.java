package com.hunglp.threadschedulemonitoroverview.task_viettel.automation_config_url;

/*
    1. Bài toán
        - Chương trình có 2 url connect tới web socket để decode voice to text
        - Ban đầu ý tưởng là select lần luợt url1, url2,.... để đảm bảo k bị tràn RAM, CPU
        - Tuy nhiên vẫn xảy ra trường hợp tràn ram,cpu do bộ nhớ ít, hiện tại đang phải restart bằng cơm
    2. Yêu cầu:
        - Tìm cách tối ưu bằng cách tạo schedule (cronjob) restart url mà đang k có client nào thực hiện connect tới
    3. Implement:
        ...

* */



