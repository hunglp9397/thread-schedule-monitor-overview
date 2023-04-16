package com.hunglp.threadschedulemonitoroverview.event_example.ex_1;
/*
    1. Cơ bản về Event & Listener

        Cơ bản là khi chương trình của  đang vận hành, và có một công việc gì đó,  không muốn xử lý trực tiếp tại Class hiện hành hoặc muốn thông báo cho các Đối tượng khác biết  vừa làm gì.

        Thì  sẽ bắn ra một object gọi là Event (sự kiện), có hoặc không thông tin đi kèm, và nhiệm vụ của các thằng khác là đón lấy hay lắng nghe sự kiện đó để xử lý nghiệp vụ của riêng nó, thằng xử lý gọi là Listener.

        Thằng gây ra sự kiện gọi là Source.

        Còn thằng cầm sự kiện đó ném cho Listener gọi là Pushlisher


     2.Áp vào thực tế
        Giả sử:
            Có một cái chuông cửa, khi có người tới bấm cái chuông này. Chuông sẽ phát ra tiếng kêu.
            Ở trong nhà có chó, nó nghe thấy tiếng kêu, nó sẽ sủa lên.

        Thì:
            Source: Người bấm chuông cửa, là người gây ra sự kiện.
            Event: sự kiện bấm chuông cửa
            Pushlisher: Cái chuông phát ra âm thanh (sự kiện) để thông báo.
            Listener: Con chó lắng nghe và xử lý sự kiện
* */