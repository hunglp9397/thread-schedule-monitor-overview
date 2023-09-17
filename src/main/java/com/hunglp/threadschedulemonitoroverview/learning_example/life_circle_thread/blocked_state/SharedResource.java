package com.hunglp.threadschedulemonitoroverview.learning_example.life_circle_thread.blocked_state;

public class SharedResource {
    public synchronized void doSomething() {
        System.out.println(Thread.currentThread().getName() + " is doing something.");
        System.out.println("Current State : " + Thread.currentThread().getState());

        // Simulate some work
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void doSomethingElse() {
        System.out.println(Thread.currentThread().getName() + " is doing something else.");
        System.out.println("Current State : " + Thread.currentThread().getState());
    }
}
