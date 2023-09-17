package com.hunglp.threadschedulemonitoroverview.learning_example.multithread.deadlock;

import com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multual_exclusive.BankAccount;

public class Main3 {
    public static void main(String[] args) {
        // Khai báo tài khoản của anh chồng và cô vợ riêng
        BankAccount3 husbandAccount = new BankAccount3("Husband's Account");
        BankAccount3 wifeAccount = new BankAccount3("Wife's Account");

        // Anh chồng muốn chuyển 3 triệu từ tài khoản của ảnh qua tài khoản cô vợ
        Thread husbandThread = new Thread() {
            @Override
            public void run() {
                husbandAccount.transferTo(wifeAccount, 3000000);
            }
        };

        // Cô vợ muốn chuyển 2 triệu từ tài khoản của cổ qua tài khoản của anh chồng
        Thread wifeThread = new Thread() {
            @Override
            public void run() {
                wifeAccount.transferTo(husbandAccount, 2000000);
            }
        };

        // Hai người thực hiện lệnh chuyển tiền gần như đồng thời
        husbandThread.start();
        wifeThread.start();
    }
}
