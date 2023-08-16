package com.dojo.dp;

import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * Given two integer arrays that represent the weights and profits of N items, 
     * implement a function knapSack() that finds a subset of these items that will gives us 
     * the maximum profit without their cumulative weight exceeding a given number capacity. 
     * Each item can only be selected once, which means we either skip it or put it in the knapsack.
     * 
     * @param profits int[] The profit that can be gained by each piece of the jewelry
     * @param weights int[] The weight of each piece of jewelry
     * @param capacity int The maximum weight that the knapsack can hold
     * @return int The maximum profit that can be returned 
     */
    public static int knapsack(int profits[], int[] weights, int capacity) {
        if (profits.length == 0 || weights.length == 0 || capacity == 0) return 0;

        if (profits.length != weights.length) return 0;  // invalid input

        // For each item, calculate the profit and weight if it is included or excluded 
        // in the knapsack and keep the maximum value
        int[] dp = new int[capacity + 1]; 
        for (int i = 0; i < profits.length; i++) {
            for (int c = capacity; c >= weights[i]; c--) {
                if (weights[i] <= c) {
                    dp[c] = dp[c - weights[i]] + profits[i];
                } else {
                    dp[c] = Math.max(dp[c], profits[i]);
                }
            }
        }
        return dp[capacity];
    }

    /**
     * Given an array of integers, write a function to find if any two subsets of the input array 
     * exist such that the sum of both subsets is equal. You can assume that the array will only 
     * consist of positive integers.
     * 
     * @param nums int[] a list of positive integers
     * @return boolean True if can be partitioned.
     */
    public static boolean canPartition(int[] nums) {
        if (nums.length == 0) return false;

        int total = 0;
        for (int n : nums) {
            total += n;
        }
        if (total % 2 != 0) return false;

        total = total / 2;  // the sum of the subarray

        // An array to indicate is the number can be included in a subarray
        boolean[] dp = new boolean[total + 1];
        dp[0] = true; // can include 0 in the subarray
        for (int i = 0; i < nums.length; i++) {
            for (int j = total; j >= nums[i]; j--) {
                if (nums[i] <= j) {
                    dp[j] = dp[j - nums[i]] || dp[j];
                } 
            }
            if (dp[total]) return true;
        }
        return false;
    }

    /**
     * Given two strings, s1 and s2, write a function that finds and 
     * returns the length of the longest substring of s2 and s1 (if any exist).
     * 
     * @param s1
     * @param s2
     * @return int the length of the longest common string if exist; otherwise, return 0
     */
    public static int lcsLength(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            char c1 = s1.charAt(i-1);
            for (int j = 1; j <= s2.length(); j++) {
                if (c1 == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
    
    /**
     * Given two strings, write a function to find the length of their shortest common superstring. 
     * A superstring is a string that has both input strings as substrings.
     * 
     * @param s1 String
     * @param s2 String
     * @return int
     */
    public static int findSCSLength(String s1, String s2) {
        if (s1 == null || s1.isEmpty()) {
            if (s2 == null || s2.isEmpty()) return 0;
            else return s2.length();
        }
        if (s2 == null || s2.isEmpty()) {
            if (s1 == null || s1.isEmpty()) return 0;
            else return s1.length();
        }

        // Step 1: initialize the table
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][];
        for (int i = 0; i <= n; i++) {
            dp[i] = new int[m+1];
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[0][j] = j;  // if s1 is empty
                } else if (j == 0) {
                    dp[i][0] = i;  // if s2 is empty
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        // Step 2: compare each char in the strings
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // the chars are the same
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;  // increase the length by 1
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1; // take the longest and increase 1
                }
            }
        }

        return dp[n][m];
    }

    /**
     * Given two strings, write a function to find the length of their shortest common superstring. 
     * A superstring is a string that has both input strings as substrings.
     * 
     * @param s1 String
     * @param s2 String
     * @return String
     */
    public static String findSCS(String s1, String s2) {
        // TO DO
        return "";
    }

    /**
     * Given a string, find the length of its longest palindromic subsequence. 
     * In a palindromic subsequence, elements read the same backward and forward.
     * A subsequence is a sequence that can be derived from another sequence 
     * by deleting some or no elements without changing the order of the remaining elements.
     * 
     * @param s
     * @return
     */
    public static int LPSLength(String s) {
        if (s == null || s.isEmpty()) return 0;

        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        int maxLength = 0;
        for (int i = 1; i <= n; i++) {
            char c1 = s.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = s.charAt(n - j);
                if (c1 == c2) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return maxLength;
    }
}
