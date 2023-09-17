package com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multithread_cooperation;

import com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multual_exclusive.BankAccount;

public class Main2 {

    public static void main(String[] args) {
        BankAccount2 bankAccount2 = new BankAccount2();

        // Cô vợ muốn rút 10 triệu VND (bạn chú ý khi này tiền không đủ để rút)
        WithDraw2Thread wifeThread = new WithDraw2Thread("Wife", bankAccount2, 10000000);
        wifeThread.start();

        // Anh chồng nạp vào 5 triệu VND
        DepositThread husbandThread = new DepositThread("Husband", bankAccount2, 5000000);
        husbandThread.start();
    }
}
