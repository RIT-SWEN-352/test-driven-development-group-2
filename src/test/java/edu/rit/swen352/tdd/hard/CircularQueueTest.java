package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
      , () -> assertEquals(CircularQueue.DEFAULT_CAPACITY, ring_buffer.capacity)
    );
  }
}
