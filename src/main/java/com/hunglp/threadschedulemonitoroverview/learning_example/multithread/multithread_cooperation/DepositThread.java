package com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multithread_cooperation;

import com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multual_exclusive.BankAccount;

public class DepositThread  extends Thread{

    String threadName = "";
    long depositAmount = 0;
    BankAccount2 bankAccount2;

    public DepositThread(String threadName, BankAccount2 bankAccount2, long depositAmount) {
        this.threadName = threadName;
        this.bankAccount2 = bankAccount2;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        bankAccount2.deposit(threadName, depositAmount);
    }
}
