package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link CircularQueue} component.
 */
class CircularQueueTest {
  @Test
  @DisplayName("ctor without size")
  void ctor_1(){
    final CircularQueue<Integer> ring_buffer = new CircularQueue<>();
    assertAll("group ctor assertions"
      , () -> assertNotNull(ring_buffer)
      , () -> assertEquals(CircularQueue.DEFAULT_CAPACITY, ring_buffer.getCapacity())
    );
  }

  @ParameterizedTest(name = "Test capacity={0}")
  @CsvSource({"1", "10", "16", "100", "256"})
  @DisplayName("ctor with valid size")
  void ctor_2(int capacity){
    final CircularQueue<Integer> ring_buffer = new CircularQueue<>(capacity);
    assertAll("group ctor assertions"
      , () -> assertNotNull(ring_buffer)
      , () -> assertEquals(capacity, ring_buffer.getCapacity())
    );
  }

  @ParameterizedTest(name = "Test capacity={0}")
  @CsvSource({"0", "-10", "-16", "-100", "-256"})
  @DisplayName("ctor with invalid size")
  void ctor_2_fail(int capacity){
    final Exception e = assertThrows(IllegalArgumentException.class, () -> new CircularQueue<>(capacity));
    assertEquals(CircularQueue.BAD_CAPACITY, e.getMessage());
  }

  @Test
  @DisplayName("is empty without prior action")
  void empty_1(){
    final CircularQueue<Integer> ring_buffer = new CircularQueue<>();
    assertTrue(ring_buffer.isEmpty());
  }
}
