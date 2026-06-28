package edu.rit.swen352.tdd.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("New list size is zero")
    void testSizeNewList() {
        MyList<String> list = new MyList<>(10);

        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("List size is ten")
    void testExistingSizeList() {
        MyList<String> list = new MyList<>(10);

        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("Adding one element increases size")
    void testAddOneElement() {
        MyList<String> list = new MyList<>(10);

        list.add("A");

        assertEquals(1, list.size());
    }

}
