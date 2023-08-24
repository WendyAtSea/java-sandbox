package com.dojo.mt;

import java.util.HashSet;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProducerConsumerTests {
    @Test
    public void testProducerAndConsumer() {
        final Random random = new Random();
        final ProducerConsumer pc = new ProducerConsumer();

        HashSet<Thread> producers = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            final int val = i;
            Thread p = new Thread(new Runnable() {
                public void run() {
                    pc.produce(val);
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            producers.add(p);
            p.start();
        }

        HashSet<Thread> consumers = new HashSet<>();
        for (int i = 0; i < 2; i++) {
            Thread c = new Thread(new Runnable() {
                public void run() {
                    pc.consume();
                    try {
                        Thread.sleep(random.nextInt(500));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            consumers.add(c);
            c.start();
        }

        Thread stopThread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);  // pause for 2 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pc.isRunning.set(false);
            }
        });
        stopThread.start();
        
        for (Thread t : producers) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Assertions.fail("Failed to produce.", e);
            }
        }
        for (Thread t : consumers) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Assertions.fail("Failed to consume.", e);
            }
        }
        try {
            stopThread.join();
        } catch (InterruptedException e) {
            Assertions.fail("Failed to stop.", e);
        }
    }
}
