package com.hunglp.threadschedulemonitoroverview.learning_example.life_circle_thread.blocked_state;

public class Main {
    public static void main(String[] args) {
        final SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(() -> {

            sharedResource.doSomething();

        });

        Thread thread2 = new Thread(() -> {
            sharedResource.doSomethingElse();
        });

        thread1.start();
        thread2.start();
    }
}

