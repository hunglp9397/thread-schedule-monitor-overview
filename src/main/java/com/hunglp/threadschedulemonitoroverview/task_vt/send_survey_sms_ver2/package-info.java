package com.hunglp.threadschedulemonitoroverview.task_vt.send_survey_sms_ver2;

/*
- Module "ABC" cần gưi thông tin paydload (khảo sát) sang module "sms"
- Ta cần gửi 1 list các survey, Mỗi survey như sau : id, suspectPhone, surveyPhone, timeSpam
- Luồng "sms"  cần phải thực hiện gửi bản tin và insert thông tin vô bảng khao sat


    ************************************************************************************
- Trong ver2 nâng cấp này, về cơ bản cách làm như sau:
 + MainAPP
 + Tại module sms api thực hiện:
    + Chia 3 việc: send, save, get thông tin
    + Tại mỗi việc, ghi thông tin ra file
    + Sau khi làm xong 3 việc thì notify cho module "ABC"
    + Module "sms" sẽ trả 1 api về kết quả thoogn tin gửi thành công hay chưa để module "ABC" gọi

*/
