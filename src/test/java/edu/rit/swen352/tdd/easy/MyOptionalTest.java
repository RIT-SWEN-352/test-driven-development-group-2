package edu.rit.swen352.tdd.easy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

/**
 * Test suite for the {@link MyOptional} component.
 */
class MyOptionalTest {

    @Test
    @DisplayName("test for of returning null")
    void of_1() {
            assertThrows(NullPointerException.class, () -> {MyOptional.of(null);
        });
    }

    @Test
    @DisplayName("test for empty returning an empty MyOptional object")
    void empty_1() {
        MyOptional<String> CuT = MyOptional.empty();

        assertFalse(CuT.isPresent());
    }

    @Test
    @DisplayName("test for ofNullable returning an empty MyOptional object")
    void ofNullable_1() {
        MyOptional<String> CuT = MyOptional.ofNullable(null);
        assertFalse(CuT.isPresent()); 
    }

    @Test
    @DisplayName("test for get properly returning an int")
    void get_1() {
        MyOptional<Integer> CuT = MyOptional.of(10);
        assertEquals(CuT.get(), 10);
    }

    @Test
    @DisplayName("test for get properly returning a string")
    void get_2() {
        MyOptional<String> CuT = MyOptional.of("Hello, World!");
        assertEquals(CuT.get(), "Hello, World!");
    }

    @Test
    @DisplayName("test for get with a null/empty MyOptional object")
    void get_3() {
        MyOptional<String> CuT = MyOptional.empty();
        assertThrows(NoSuchElementException.class, () -> CuT.get());
    }

    

}
