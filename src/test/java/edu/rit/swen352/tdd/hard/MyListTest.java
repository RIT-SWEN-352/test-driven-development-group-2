package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link MyList} component.
 */
class MyListTest {
     
    @Test
    @DisplayName("Constructor creates list")
    void testConstructor() {
        MyList<String> list = new MyList<>(10);

        assertNotNull(list);
    }

    @Test
    @DisplayName("New list is empty")
    void testIsEmptyNewList() {
        MyList<String> list = new MyList<>(10);

        assertTrue(list.isEmpty());
    }
}
