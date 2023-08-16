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
        String s1 = "www.educative.io/explore";
        String s2 = "educative.io/edpresso";
        int value = Solutions.lcsLength(s1, s2);
        Assertions.assertEquals(17, value);

        s1 = "xyzabcdefgABCabcd";
        s2 = "abcdorabcdefgh";
        value = Solutions.lcsLength(s1, s2);
        Assertions.assertEquals(8, value);

        s1 = "aaa";
        s2 = "aa";
        value = Solutions.lcsLength(s1, s2);
        Assertions.assertEquals(2, value);
        
        s1 = "efgh";
        s2 = "abc";
        value = Solutions.lcsLength(s1, s2);
        Assertions.assertEquals(0, value);
        
        s1 = "aaaa";
        s2 = "aaaa";
        value = Solutions.lcsLength(s1, s2);
        Assertions.assertEquals(4, value);
    }

    @Test
    public void testSCSLength() {
        String s1 = "abcf";
        String s2 = "bdcf";
        int value = Solutions.findSCSLength(s1, s2);
        Assertions.assertEquals(5, value);  // abdcf

        s1 = "aaa";
        s2 = "abc";
        value = Solutions.findSCSLength(s1, s2);
        Assertions.assertEquals(5, value); // aaabc

        s1 = "www.educative.io/explore";
        s2 = "educative.io/edpresso";
        value = Solutions.findSCSLength(s1, s2);
        Assertions.assertEquals(28, value); 

        s1 = "EducativeIsFun";
        s2 = "AlgorithmsAreFun";
        value = Solutions.findSCSLength(s1, s2);
        Assertions.assertEquals(25, value); 
    }

    // skip for now
    public void testSCS() {
        String s1 = "abcf";
        String s2 = "bdcf";
        String value = Solutions.findSCS(s1, s2);
        Assertions.assertEquals("abdcf", value);  // abdcf

        s1 = "aaa";
        s2 = "abc";
        value = Solutions.findSCS(s1, s2);
        Assertions.assertEquals("aaabc", value); // aaabc

        s1 = "www.educative.io/explore";
        s2 = "educative.io/edpresso";
        value = Solutions.findSCS(s1, s2);
        Assertions.assertEquals("www.educative.io/exdperlessore", value); 

        s1 = "EducativeIsFun";
        s2 = "AlgorithmsAreFun";
        value = Solutions.findSCS(s1, s2);
        Assertions.assertEquals("", value); 
    }

    @Test
    public void testLPSLength() {
        String s = "abdbca";
        int value = Solutions.LPSLength(s);
        Assertions.assertEquals(5, value);

        s = "bbbab";
        value = Solutions.LPSLength(s);
        Assertions.assertEquals(4, value);

        s = "aba";
        value = Solutions.LPSLength(s);
        Assertions.assertEquals(3, value);

        s = "abcdef";
        value = Solutions.LPSLength(s);
        Assertions.assertEquals(1, value);
    }
}
