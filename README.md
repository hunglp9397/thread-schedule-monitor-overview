# NOTE :  Ví dụ về thread, multithread, monitor, schedule (Bao gồm các task ở VT) Ở mỗi package có file package-info miêu tả require example hoặc cách implement, giải thích

------------------------------------------------------------------------------------------------------------------------
# JAVA CONCURRENCY

## 1. Vòng đời của một Thread
- ![img.png](IMG_README/img.png)
- ![2.png](IMG_README/2.png)
-  Giải thích:
   + NEW : Trạng thái tạo mới của 1 Thread, chưa gọi phương thức start()
   + RUNNABLE: Trạng thái đưa Thread vào hàng đợi cấp tài nguyên ( Sau khi gọi phương thức start())
   + Trong quá trình Thread đang chạy, nếu có bất kỳ tác động nào đó (ngoại trừ làm kết thu vòng đời của Thread) Thread sẽ vòa trạng thái BLOCK, WAITING, TIMED_WAITING
   + BLOCKED : Không có đủ điều kiện để chạy (Ví dụ trong một app đồng bộ, thì chỉ một Thread được sử dụng đến tài nguyên dùng chung, Các Thread còn lại sẽ phải chờ ợi cho THread ưu tiên kia sử dụng xong tài nguyên)
   + WAITING : Trạng thái Thread phải đợi 1 Thread nào đó hoàn thành tác vụ
   + TIMED_WAITING : Trạng thái Thread đợi 1 Thread nào đó trong 1 khoảng tgian xác định
   + TERMINATED : Trạng thái kết thúc vòng đời Thread


## 2 Cơ chế Monitor - Lock