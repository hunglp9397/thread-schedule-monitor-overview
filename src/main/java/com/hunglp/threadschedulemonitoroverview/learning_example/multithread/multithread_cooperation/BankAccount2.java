package com.hunglp.threadschedulemonitoroverview.learning_example.multithread.multithread_cooperation;

public class BankAccount2 {

    long amount = 5000000;

    public boolean isValidAccountBalance(long withDrawAmount) {
        try {
            Thread.sleep(2000); // Giả lập tgian query DB
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
    public synchronized void withdraw(String threadName, long withdrawAmount) {
        System.out.println("ThreadName : " + threadName + " want withdraw : " + withdrawAmount);

        synchronized (this) {
            if (isValidAccountBalance(withdrawAmount)) {

                try {
                    Thread.sleep(2000);
                    // Giả lập thời gian rút tiền và cập nhật số tiền còn lại vào cơ sở dữ liệu
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

    public synchronized void withdrawWhenBalanceEnough(String threadName, long withdrawAmount) {
        // In thông tin người rút
        System.out.println(threadName + "  want withdraw: " + withdrawAmount);
        if (!isValidAccountBalance(withdrawAmount)) {
            // Nếu không đủ tiền, thì đợi cho đến khi có đủ tiền thì rút
            System.out.println(threadName + " wait for balance enough");
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(2000); // Giả lập tgian rút tiền, update database
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Đủ tiền, hoặc không còn đợi nữa, thì được phép rút
        amount -= withdrawAmount;
        System.out.println(threadName + " withdraw successful: " + withdrawAmount);
    }

    public synchronized void deposit(String threadName, long depositAmount) {
        // In thông tin người nạp tiền
        System.out.println(threadName + " deposit: " + depositAmount);

        try {
            Thread.sleep(2000);  // Giả lập thời gian nạp tiền và cập nhật số tiền mới vào cơ sở dữ liệu
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        amount += depositAmount;
        // Đánh thức đối tượng đang ngủ và chờ có tiền thì rút
        notify();
    }
}
