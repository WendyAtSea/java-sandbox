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

}
