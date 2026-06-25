package edu.rit.swen352.tdd.easy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    

}
