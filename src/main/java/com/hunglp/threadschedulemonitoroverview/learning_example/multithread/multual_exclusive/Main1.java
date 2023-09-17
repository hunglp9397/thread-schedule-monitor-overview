package com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multual_exclusive;

public class Main1 {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();

        System.out.println("Current Amount:" + bankAccount.getAmount());
        System.out.println("------------------------------------------");

        // Người chồng rút 15 tr
        WithDrawThread husbandThread = new WithDrawThread("HusbandThread", bankAccount,15000000);
        husbandThread.start();

        // Người vợ rút hết tiền
        WithDrawThread wifeThread = new WithDrawThread("WifeThread", bankAccount, 20000000);
        wifeThread.start();
    }


}
