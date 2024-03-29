package com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multual_exclusive;


import lombok.Data;

@Data
public class BankAccount {

    long amount = 20000000;

    public boolean checkAccountBalance(long withDrawAmount) {
        try {
            Thread.sleep(1000); // Giả lập tgian query DB
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (withDrawAmount <= amount) {
            // Cho phép rút tiền
            return true;
        }

        // Không cho phép rút tiền
        return false;
    }

    public synchronized void withDraw(String threadName, long withdrawAmount) {
        System.out.println("ThreadName : " + threadName + " want withdraw : " + withdrawAmount);

        synchronized (this) {
            if (checkAccountBalance(withdrawAmount)) {
                try {
                    System.out.println("Processing...");
                    Thread.sleep(1000);
                    // Giả lập thời gian rút tiền và  cập nhật số tiền còn lại vào cơ sở dữ liệu
                } catch (InterruptedException e) {
                    System.out.println("withdraw error!");
                }

                this.amount -= withdrawAmount;
                System.out.println(threadName + " withdraw successful: " + withdrawAmount);
            } else {
                System.out.println("Withdraw error! Account balance not enough ");
            }

        }


        // In ra số dư tài khoản
        System.out.println(threadName + " account balance: " + this.amount);
    }


}
