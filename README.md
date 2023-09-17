# NOTE :  Ví dụ về thread, multithread, monitor, schedule (Bao gồm các task ở VT) Ở mỗi package có file package-info miêu tả require example hoặc cách implement, giải thích

------------------------------------------------------------------------------------------------------------------------
# JAVA CONCURRENCY

## 1. Vòng đời của một Thread
- ![img.png](IMG_README/img.png)
-  Giải thích:
   + NEW : Trạng thái tạo mới của 1 Thread, chưa gọi phương thức start()
   + RUNNABLE: Trạng thái đưa Thread vào hàng đợi cấp tài nguyên ( Sau khi gọi phương thức start())
   + Trong quá trình Thread đang chạy, nếu có bất kỳ tác động nào đó (ngoại trừ làm kết thu vòng đời của Thread) Thread sẽ vòa trạng thái BLOCK, WAITING, TIMED_WAITING
   + BLOCKED : 
   + WAITING :
   + TIMED_WAITING :
   + TERMINATED : Trạng thái kết thúc vòng đời Thread
   + 