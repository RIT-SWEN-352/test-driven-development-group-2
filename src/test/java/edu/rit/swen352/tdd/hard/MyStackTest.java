package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link MyStack} component.
 */
class MyStackTest {

    @Test
    @DisplayName("ctor sets capacity correctly")
    void MyStack_1() {
        MyStack<Integer> CuT = new MyStack<>(10);

        assertEquals(10, CuT.capacity);
    }

}
