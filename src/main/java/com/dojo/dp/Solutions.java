package com.dojo.dp;

/* Dynamic Programming */
public class Solutions {
    /**
     * Return the ith Fibonacci number.
     * 
     * Fibonacci series: 0, 1, 1, 2, 3, 5, 8, ...
     * 
     * Example: 
     *  fibonacci(1) -> 1
     *  fibonacci(7) -> 13
     * 
     * @param i
     * @return int
     */
    public static int fibonacci(int i) {
        if (i < 1) return 0;
        if (i == 1) return 1;

        int n_2 = 0;
        int n_1 = 1;
        for (int n = 2; n <= i; n++) {
            int tmp = n_1 + n_2;
            n_2 = n_1;
            n_1 = tmp;
        }
        return n_1;
    }
}
