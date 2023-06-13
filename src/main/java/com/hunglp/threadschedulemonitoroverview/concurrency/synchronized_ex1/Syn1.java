package com.hunglp.threadschedulemonitoroverview.concurrency.synchronized_ex1;

public class Syn1 {

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int calculate(){
        num +=1;
        return num;
    }
}
