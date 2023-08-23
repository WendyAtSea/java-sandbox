package com.dojo.mt;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Imagine you have five philosopher's sitting on a roundtable. The philosopher's do 
 * only two kinds of activities. One they contemplate, and two they eat. 
 * However, they have only five forks between themselves to eat their food with. 
 * Each philosopher requires both the fork to his left and the fork to his right to eat his food.
 * 
 * The arrangement of the philosophers and the forks are shown in a round table with 
 * one fork in between each philosopher:
 *      P0 | F0 | P1 | F1 | P2 | F2 | P3 | F3 | P4 | F4 | P0
 * 
 * Design a solution where each philosopher gets a chance to eat his food 
 * without causing a deadlock.
 */
public class DiningPhilosophers {
    // Set a time for a philosopher to contemplate
    private static Random random = new Random();

    private Semaphore[] forks;
    private Semaphore maxDinners;
    private final int n;
    private int count;

    public DiningPhilosophers(int n) {
        this.count = n * 2;
        this.n = n;  // n >= 2
        this.forks = new Semaphore[n];
        for (int i = 0; i < n; i++) {
            this.forks[i] = new Semaphore(1, false);
        }

        this.maxDinners = new Semaphore(n-1, false);
    }
    
    public void philosopherLifeCycle(int id) throws InterruptedException {
        // Set a limit 
        while (count > 0) {
            contemplate(id);
            eat(id);
            count--;
        }
        System.out.println("--- Philosopher, " + id + " is finished.");
    }

    private void contemplate(int id) throws InterruptedException {
        System.out.println("Philosopher, " + id + " is contemplating...");
        Thread.sleep(random.nextInt(5000));  // sleep upto 5 seconds
        //System.out.println("  Philosopher, " + id + " stopped contemplating");
    }

    private void eat(int id) throws InterruptedException {
        maxDinners.acquire();

        forks[id].acquire();
        forks[(id + n - 1) % n].acquire();
        System.out.println(">>> Philosopher, " + id + " is eating...");

        Thread.sleep(random.nextInt(2000));  // sleep upto 2 seconds

        forks[id].release();
        forks[(id + n - 1) % n].release();
        maxDinners.release();
    }
}
