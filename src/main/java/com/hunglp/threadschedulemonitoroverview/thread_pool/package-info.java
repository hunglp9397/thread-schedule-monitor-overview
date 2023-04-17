package com.hunglp.threadschedulemonitoroverview.thread_pool;
/*
    1.Thread Pool
        - Thread hoạt đông tốn ke vì đòi hỏi hệ điều hành cung cấp tài nguyeen để thực thi ta vụ

        - Thread Pool giới hạn số lượng Thread được chạy bên trong ứng dụng trong cùng 1 thời điểm

        VD: Chương trình tải tập tin từ Internet, Mỗi tập tin cần 1 thread để thực hiện tải file
            ->Cần tới 100 thread hoạt động trong cùng 1 thời điểm
            -> Giảm hiệu suất, có thể gây crash

        Solution: Thay vì tạo các thread mới, Một ThreadPool sẽ giữ 1 số thread nhàn rỗi (no task) để sẵn sàng thực hiện task nếu cần

        


* */