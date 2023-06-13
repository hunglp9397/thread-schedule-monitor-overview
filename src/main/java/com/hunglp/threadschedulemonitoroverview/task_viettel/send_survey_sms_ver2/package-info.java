package com.hunglp.threadschedulemonitoroverview.task_viettel.send_survey_sms_ver2;

/*
- Module "anti-business-call" cần gưi thông tin paydload (survey) sang module "sms-api"
- Ta cần gửi 1 list các survey, Mỗi survey như sau : suspectPhone, surveyPhone, timeSpam
- Luồng "sms-api"  cần phải thực hiện gửi bản tin và insert thông tin vô bảng survey
- Dữ liệu trả ra là json String:
    {
        totalSuccess : x,
        totalFail : y,
        listSurveySend : [
            {
                suspectPHone : z,
                surveyPhone : k,
                isSuccess : y
            },
            {
                suspectPHone : z,
                surveyPhone : k,
                isSuccess : y
            }
        ]

    }

*/