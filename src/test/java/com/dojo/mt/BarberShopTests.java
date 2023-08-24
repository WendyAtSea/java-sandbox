package com.dojo.mt;

import java.util.HashSet;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class BarberShopTests {
    @Test
    public void testBarberShop() {
        Random random = new Random();
        HashSet<Thread> customers = new HashSet<>();
        final BarberShop barberShop = new BarberShop(5);

        Thread barber = new Thread(new Runnable() {
            public void run() {
                try {
                    barberShop.barber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        barber.start();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        barberShop.customerWalkIn();
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            customers.add(t);
        }

        for (Thread t : customers) {
            t.start();
        }
        for (Thread t : customers) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        customers.clear();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            barber.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
