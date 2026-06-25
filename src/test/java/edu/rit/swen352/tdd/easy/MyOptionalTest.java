package edu.rit.swen352.tdd.easy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Test suite for the {@link MyOptional} component.
 */
class MyOptionalTest {

    @Test
    @DisplayName("test for of returning null")
    void of_1() {
            assertThrows(NullPointerException.class, () -> {MyOptional.of(null);}, "of correctly returned null");
    }

    @Test
    @DisplayName("test for empty returning an empty MyOptional object")
    void empty_1() {
        MyOptional<String> CuT = MyOptional.empty();

        assertFalse(CuT.isPresent(), "empty returns an empty MyOptional object");
    }

    @Test
    @DisplayName("test for ofNullable returning an empty MyOptional object")
    void ofNullable_1() {
        MyOptional<String> CuT = MyOptional.ofNullable(null);
        assertFalse(CuT.isPresent(), "ofNullable returns an empty MyOptional object"); 
    }

    @Test
    @DisplayName("test for get properly returning an int")
    void get_1() {
        MyOptional<Integer> CuT = MyOptional.of(10);
        assertEquals(CuT.get(), 10, "get returned an int");
    }

    @Test
    @DisplayName("test for get properly returning a string")
    void get_2() {
        MyOptional<String> CuT = MyOptional.of("Hello, World!");
        assertEquals(CuT.get(), "Hello, World!", "get returned a string");
    }

    @Test
    @DisplayName("test for get with a null/empty MyOptional object")
    void get_3() {
        MyOptional<Integer> CuT = MyOptional.empty();
        assertThrows(NoSuchElementException.class, () -> CuT.get(), "get threw an error for an empty MyOptional");
    }

    @Test
    @DisplayName("test for ifPresent executing consumer when exists")
    void ifPresent_1() {
        MyOptional<Integer> CuT = MyOptional.of(10);
        AtomicInteger result = new AtomicInteger(0);
        CuT.ifPresent(value -> result.set(value));
        assertEquals(10, result.get());
    }

    @Test
    @DisplayName("test for ifPresent doing nothing when empty")
    void ifPresent_2() {
        MyOptional<Integer> CuT = MyOptional.empty();
        AtomicInteger result = new AtomicInteger(0);
        CuT.ifPresent(value -> result.set(value));
        assertEquals(0, result.get());
    }

    

}
