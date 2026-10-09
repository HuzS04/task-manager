package com.huzaifah.task_manager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void shouldAddTwoNumbers() {
        // Arrange — set up the data
        int a = 5;
        int b = 3;

        // Act — run the thing you're testing
        int result = a + b;

        // Assert — check the result is what you expected
        assertEquals(8, result);
    }

    @Test
    void shouldReturnTrueForEvenNumber() {
        int number = 4;
        boolean result = number % 2 == 0;
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForOddNumber() {
        int number = 5;
        boolean result = number % 2 == 0;
        assertFalse(result);
    }
}