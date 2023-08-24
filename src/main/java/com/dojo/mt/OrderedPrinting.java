package com.dojo.mt;

import java.util.concurrent.Semaphore;

public class OrderedPrinting {
    private final Semaphore latch1;
    private final Semaphore latch2;

    public OrderedPrinting() {
        this.latch1 = new Semaphore(0);
        this.latch2 = new Semaphore(0);
    }

    public void printFirst() {
        System.out.println("First!");
        this.latch1.release();
    }

    public void printSecond() throws InterruptedException {
        this.latch1.acquire();
        System.out.println("Second!");
        this.latch2.release();
    }

    public void printThird() throws InterruptedException {
        this.latch2.acquire();
        System.out.println("Third!");
    }
}
