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

        assertEquals(10, CuT.getCapacity());
    }

    @Test
    @DisplayName("ctor defaults to 16")
    void MyStack_2() {
        MyStack<Integer> CuT = new MyStack<>();
        assertEquals(16, CuT.getCapacity());
    }

    @Test
    @DisplayName("getCapacity returns correct capacity")
    void getCapacity_1() {
        MyStack<Integer> CuT = new MyStack<>(1000);
        assertEquals(1000, CuT.getCapacity());
    }

    @Test
    @DisplayName("isEmpty returning true for empty stack")
    void isEmpty_1() {
        MyStack<Integer> CuT = new MyStack<>();
        assertTrue(CuT.isEmpty());
    }

    @Test
    @DisplayName("isEmpty returning false for non-empty stack")
    void isEmpty_2() {
            MyStack<Integer> CuT = new MyStack<>();
            CuT.push(10);
            assertFalse(CuT.isEmpty());
    }

    @Test
    @DisplayName("size is 0 for empty stack")
    void size_1() {
        MyStack<Integer> CuT = new MyStack<>();
        assertEquals(CuT.size(), 0);
    }

    @Test
    @DisplayName("size is not 0 for non-empty stack")
    void size_2() {
            MyStack<Integer> CuT = new MyStack<>();
            CuT.push(10);
            assertEquals(1, CuT.size());
    }

    @Test
    @DisplayName("push adds elements to stack")
    void push_1() {
        MyStack<Integer> CuT = new MyStack<>();
        CuT.push(5);
        CuT.push(10);
        assertEquals(CuT.size(), 2);
    }



}
