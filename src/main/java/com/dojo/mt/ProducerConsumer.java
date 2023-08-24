package com.dojo.mt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The producer's job generates data and puts into a buffer continuously.
 * The consumer's job consumes data one at a time
 * 
 * Make sure the producer will put more data into a buffer and 
 * consume won't try to remove data from an empty buffer.
 */
public class ProducerConsumer {
    private BlockingQueue<String> queue = new LinkedBlockingDeque<>(5);
    public AtomicBoolean isRunning = new AtomicBoolean(true);
    private AtomicInteger counter = new AtomicInteger(0);

    public void produce(int value) {
        while (isRunning.get()) {
            String msg = "" + value + " - " + counter.getAndIncrement();
            try {
                boolean status = queue.add(msg);
                if (status) {
                    System.out.println(">>> Produce, " + msg);
                } else {
                    System.out.println("Discard message, " + msg);
                }
            } catch (Exception e) {
                //System.out.println("Queue is full. Discard " + msg);
            }
        }
        System.out.println("Producer is closed.");
    }

    public void consume() {
        while (isRunning.get() || !queue.isEmpty()) {
            try {
                String value = queue.take();
                System.out.println("  <<< Consume, " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All done!");
    }
}
