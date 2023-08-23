package com.dojo.mt;

import org.junit.jupiter.api.Test;

class Philosopher extends Thread {
    final DiningPhilosophers dp;
    final int id;

    public Philosopher(DiningPhilosophers dp, int id) {
        this.dp = dp;
        this.id = id;
    }

    public void run() {
        try {
            this.dp.philosopherLifeCycle(this.id);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}

public class DiningPhilosophersTest {
    @Test
    public void FivePhilosophers() {
        int n = 5;
        final DiningPhilosophers dp = new DiningPhilosophers(n);

        Philosopher[] philosophers = new Philosopher[5];
        // Initialize 
        for (int i = 0; i < n; i++) {
            philosophers[i] = new Philosopher(dp, i);
        }

        // start
        for (int i = 0; i < n; i++) {
            philosophers[i].start();
        }

        // wait 
        for (int i = 0; i < n; i++) {
            try {
                philosophers[i].join();  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
