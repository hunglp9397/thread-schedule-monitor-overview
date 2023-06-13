package com.hunglp.threadschedulemonitoroverview.concurrency;

public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("hello"));

        thread.start();
    }
}
