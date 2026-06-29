package edu.rit.swen352.tdd.hard;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("Adding one element increases size")
    void testAddOneElement() {
        MyList<String> list = new MyList<>(10);

        list.add("A");

        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("List with elements is not empty")
    void testIsEmptyFalseAfterAdd() {
        MyList<String> list = new MyList<>(10);

        list.add("A");

        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("Get returns added element")
    void testGetElement() {
        MyList<String> list = new MyList<>(10);

        list.add("A");

        assertEquals("A", list.get(0));
    }

    @Test
    @DisplayName("Get throws when index outside size")
    void testGetInvalidIndex() {
        MyList<String> list = new MyList<>(10);

        assertThrows(
            NoSuchElementException.class,
            () -> list.get(0)
        );
    }

    @Test
    @DisplayName("Remove decreases size")
    void testRemove() {
        MyList<String> list = new MyList<>(10);

        list.add("A");
        list.add("B");

        list.remove(0);

        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Remove throws when index outside size")
    void testRemoveInvalidIndex() {
        MyList<String> list = new MyList<>(10);

        assertThrows(
            NoSuchElementException.class,
            () -> list.remove(0)
        );
    }

    @Test
    @DisplayName("forEach visits every element")
    void testForEach() {
        MyList<Integer> list = new MyList<>(10);

        list.add(1);
        list.add(2);
        list.add(3);

        AtomicInteger sum = new AtomicInteger();

        list.forEach(sum::addAndGet);

        assertEquals(6, sum.get());
    }

}
