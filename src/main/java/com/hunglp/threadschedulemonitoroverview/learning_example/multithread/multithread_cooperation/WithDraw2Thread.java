package com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multithread_cooperation;

import com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multual_exclusive.BankAccount;

public class WithDraw2Thread extends Thread {
    String threadName = "";
    BankAccount2 bankAccount2;

    long withDrawAmount = 0;

    public WithDraw2Thread(String threadName, BankAccount2 bankAccount2, long withDrawAmount) {
        this.threadName = threadName;
        this.bankAccount2 = bankAccount2;
        this.withDrawAmount = withDrawAmount;
    }


    @Override
    public void run() {
        bankAccount2.withdraw(threadName, withDrawAmount);
    }
}
