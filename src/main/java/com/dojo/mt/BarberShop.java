package com.dojo.mt;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A barbershop consists of a waiting room with n chairs, and a barber chair for giving haircuts.
 * 1. If there are no customers to be served, the barber goes to sleep. 
 * 2. If a customer enters the barbershop and all chairs are occupied, 
 *    then the customer leaves the shop. 
 * 3. If the barber is busy, but chairs are available, 
 *    then the customer sits in one of the free chairs. 
 * 4. If the barber is asleep, the customer wakes up the barber. 
 * 
 * Write a program to coordinate the interaction between the barber and the customers.
 */
public class BarberShop {
    private Random random = new Random();

    private final int NUM_HAIR_CUTS = 5; 
    private final int chairs;
    private int numHairCutsGiven = 0;
    private int numWaitingCustomers = 0;

    // A reentrant lock is a mutual exclusion mechanism that allows threads to reenter 
    // into a lock on a resource (multiple times) without a deadlock situation.
    // A thread entering into the lock increases the hold count by one every time. 
    // Similarly, the hold count decreases when unlock is requested. 
    // Therefore, a resource is locked until the counter returns to zero.
    ReentrantLock reentrantLock = new ReentrantLock();

    Semaphore waitForCustomerToEnter = new Semaphore(0, true);
    Semaphore waitForCustomerToLeave = new Semaphore(0, true);
    Semaphore waitForBarberCutHair = new Semaphore(0, true);
    Semaphore waitForBarberToGetReady = new Semaphore(0, true);

    public BarberShop(int numOfChairs) {
        this.chairs = numOfChairs;
    }

    public void customerWalkIn() throws InterruptedException {
        reentrantLock.lock();
        if (numWaitingCustomers == chairs) {
            System.out.println("All chairs are occupied. Customer left.");
            reentrantLock.unlock();
            return;
        }

        // let customer wait
        numWaitingCustomers++;
        reentrantLock.unlock();
        System.out.println(">>> " + numWaitingCustomers + " customer waiting.");

        // notify barber
        waitForCustomerToEnter.release();
        waitForBarberToGetReady.acquire();
        
        reentrantLock.lock();
        numWaitingCustomers--;
        reentrantLock.unlock();
        System.out.println("<<< " + numWaitingCustomers + " customer waiting.");

        // wait for haircut
        waitForBarberCutHair.acquire();
        //waitForCustomerToLeave.release();
    }

    public void barber() throws InterruptedException {
        while (numHairCutsGiven < NUM_HAIR_CUTS) {
            waitForCustomerToEnter.acquire();
            waitForBarberToGetReady.release();

            numHairCutsGiven++;
            System.out.println("Cutting hair, " + numHairCutsGiven);
            Thread.sleep(random.nextInt(1000)); 

            waitForBarberCutHair.release();
            //waitForCustomerToLeave.acquire();
        }

        System.out.println("Barber shop is closed.");
    }
}
