package com.huzaifah.task_manager;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BasicJavaTest {

    @Test
    void shouldReturnTrueWhenStringContainsKeyword(){
        String text = "Buy groceries";
        boolean result = text.contains("groceries");
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStringDoesNotContainKeyword(){
        String text = "Buy groceries";
        boolean result = text.contains("milk");
        assertFalse(result);
    }

    @Test
    void shouldReturnCorrectListSize(){
        ArrayList<String> names = new ArrayList<>();
        names.add("Bob");
        names.add("Jim");
        names.add("Jon");
        names.add("Steve");
        int size = names.size();
        assertEquals(4, size);
    }

    @Test
    void shouldRemoveItemFromList(){
        ArrayList<String> names = new ArrayList<>();
        names.add("Bob");
        names.add("Jim");
        names.add("Jon");
        names.remove(0);
        int size = names.size();
        assertEquals(2, size);
    }

    @Test
    void shouldThrowExceptionWhenDividingByZero(){
        int a = 4;
        int b = 0;
        assertThrows(ArithmeticException.class, () -> {
            int result = a / b;
        });
    }
}
