package com.dojo.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SolutionsTests {

    @Test
    @DisplayName("Fibonacci series")
    public void fibonacci() {
        int value = Solutions.fibonacci(0);
        Assertions.assertEquals(0, value);

        value = Solutions.fibonacci(2);
        Assertions.assertEquals(1, value);

        value = Solutions.fibonacci(7);
        Assertions.assertEquals(13, value);

        value = Solutions.fibonacci(20);
        Assertions.assertEquals(6765, value);
    }

    @Test
    public void testKnapsack() {
        int[] profits = {1,6,10,16};
        int[] weights = {1,2,3,5};
        int profit = Solutions.knapsack(profits, weights, 7);
        Assertions.assertEquals(22, profit);

        profit = Solutions.knapsack(profits, weights, 6);
        Assertions.assertEquals(17, profit);
    }

    @Test
    public void testCanBePartitioned() {
        int[] nums1 = {1,2,3,4};
        boolean status = Solutions.canPartition(nums1);
        Assertions.assertTrue(status);

        int[] nums2 = {1,2,3,6,1,1};
        status = Solutions.canPartition(nums2);
        Assertions.assertTrue(status);
    }

    @Test
    public void testCanNotBePartitioned() {
        int[] nums1 = {1,2,3,4,1};
        boolean status = Solutions.canPartition(nums1);
        Assertions.assertFalse(status);

        int[] nums2 = {2,4,4,2,2,4};
        status = Solutions.canPartition(nums2);
        Assertions.assertFalse(status);
    }

    @Test
    public void testLCSLength() {
        String[] s1 = {"www.educative.io/explore", "xyzabcdefgABCabcd", "aaa", "efgh", "aaaa"};
        String[] s2 = {"educative.io/edpresso", "abcdorabcdefgh", "aa", "abc", "aaaa"};
        int[] expectedLengths= {14, 7, 2, 0, 4};

        for (int i = 0; i < s1.length; i++) {
            int value = Solutions.lcsLength(s1[i], s2[i]);
            Assertions.assertEquals(expectedLengths[i], value);
        }
    }
}
