package com.hunglp.threadschedulemonitoroverview.learning_example.multithread.deadlock;

import com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multual_exclusive.BankAccount;

public class BankAccount3 extends  Thread{

    long amount = 5000000;
    String accountName = "";

    public BankAccount3(String accountName){
        this.accountName = accountName;
    }

    public synchronized void withdraw(long withdrawAmount){
        System.out.println(accountName + " withdrawing...");

        // Trừ tiền
        amount -= withdrawAmount;
    }

    public synchronized void deposit(long depositAmount){
        System.out.println(accountName + " depositing ...");

        // Nạp tiền
        amount += depositAmount;
    }

    public void transferTo(BankAccount3 toAccount, long transferAmount) {
        synchronized(this) {
            // Rút tiền từ tài khoản này
            this.withdraw(transferAmount);

            synchronized(toAccount) {
                // Nạp tiền vào toAccount
                toAccount.deposit(transferAmount);
            }

            // In số dư tài khoản khi kết thúc quá trình chuyển tiền
            System.out.println("The amount of " + accountName + " is: " + amount);
        }
    }


}
