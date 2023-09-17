package com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multual_exclusive;

public class WithDrawThread extends Thread{

    String threadName  = "";
    BankAccount bankAccount;

    long withDrawAmount  = 0;

    public WithDrawThread(String threadName, BankAccount bankAccount, long withDrawAmount){
        this.threadName = threadName;
        this.bankAccount = bankAccount;
        this.withDrawAmount = withDrawAmount;
    }


    @Override
    public void run() {
       bankAccount.withDraw(threadName,withDrawAmount);
    }
}
