package com.dojo.mt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderedPrintTests {
    class PrintThread extends Thread {
        OrderedPrinting op;
        String order;
        Collection<Object> result;

        public PrintThread(OrderedPrinting op, String order, Collection<Object> result) {
            this.op = op;
            this.order = order;
            this.result = result;
        }

        public void run() {
            switch (this.order) {
                case "first":
                    op.printFirst();
                    result.add("First");
                    break;
                case "second":
                    try {
                        op.printSecond();
                        result.add("Second");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "third":
                    try {
                        op.printThird();
                        result.add("Third");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Test
    public void testOrderedPrint() {
        final OrderedPrinting op = new OrderedPrinting();
        final Collection<Object> result = Collections.synchronizedCollection(new ArrayList<>());

        PrintThread t1 = new PrintThread(op, "first", result);
        PrintThread t2 = new PrintThread(op, "second", result);
        PrintThread t3 = new PrintThread(op, "third", result);
        t3.start();
        t2.start();
        t1.start();
        try {
            t2.join();
            t3.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        String[] expected = {"First", "Second", "Third"};
        Assertions.assertArrayEquals(expected, result.toArray());
    }
}
