package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;

/*
- Module "anti-business-call" cần gưi thông tin paydload (survey) sang module "sms-api"
- Ta cần gửi 1 list các survey, Mỗi survey như sau : id, suspectPhone, surveyPhone, timeSpam
- Luồng "sms-api"  cần phải thực hiện gửi bản tin và insert thông tin vô bảng survey


    ************************************************************************************
- Trong ver2 nâng cấp này, về cơ bản cách làm như sau:
 + Tại Class MainApp giả sử gọi api /send Từ SurveyController để yêu cầu module sms-api thực hiện gửi survey
 + Tại module sms api thực hiện:
    + Chia 3 việc: send, save, get thông tin
    + Tại mỗi việc, ghi thông tin ra file
    + Sau khi làm xong 3 việc thì notify cho module "anti-business-call"
    + Module "sms-api" sẽ trả 1 api về kết quả thoogn tin gửi thành công hay chưa để module "Anti-business-call" gọi

*/
