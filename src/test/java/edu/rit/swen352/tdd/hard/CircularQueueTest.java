package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

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

  static Stream<Arguments> provideListOfElementsToAdd() {
    return Stream.of(
      Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7), 8),
      Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7, 8), 9),
      Arguments.of(List.of(1, 2, 3, 4), 5)
    );
  }

  @ParameterizedTest
  @MethodSource("provideListOfElementsToAdd")
  @DisplayName("add valid number of valid elements to buffer")
  void add_1(List<Integer> inputList, int capacity){
    final CircularQueue<Integer> ring_buffer = new CircularQueue<>(capacity);
    for (int val : inputList) {
      ring_buffer.add(val);
    }

    assertAll("group all add assertions"
      , () -> assertNotNull(ring_buffer)
      , () -> assertFalse(ring_buffer.isEmpty())
    );
  }

  @ParameterizedTest
  @MethodSource("provideListOfElementsToAdd")
  @DisplayName("add invalid number of valid elements to buffer")
  void add_1_fail(List<Integer> inputList, int capacity){
    final CircularQueue<Integer> ring_buffer = new CircularQueue<>(capacity-1);
    for (int i = 0; i < inputList.size() - 1; i++) {
      ring_buffer.add(inputList.get(i));
    }

    final Exception e = assertThrows(IllegalStateException.class, () -> ring_buffer.add(inputList.getLast()));
    assertEquals(CircularQueue.QUEUE_FULL, e.getMessage());
  }

  @ParameterizedTest
  @MethodSource("provideListOfElementsToAdd")
  @DisplayName("remove valid number of valid elements from buffer")
  void remove_1(List<Integer> inputList, int capacity){
    final CircularQueue<Integer> ring_buffer = new CircularQueue<>(capacity);
    for (int val : inputList) {
      ring_buffer.add(val);
    }

    List<Integer> removed = new ArrayList<>(capacity);
    for (int i = 0; i < inputList.size(); i++) {
      removed.add(ring_buffer.remove());
    }

    List<Integer> copyInput = new ArrayList<>(inputList);
    List<Integer> copyRemoved = new ArrayList<>(removed);

    Collections.sort(copyInput);
    Collections.sort(copyRemoved);

    assertAll("group removal assertions"
      , () -> assertEquals(copyInput, copyRemoved)
      , () -> assertTrue(ring_buffer.isEmpty())
      , () -> assertEquals(capacity-1, copyRemoved.size())
    );
  }

  @ParameterizedTest
  @MethodSource("provideListOfElementsToAdd")
  @DisplayName("remove invalid number of valid elements from buffer")
  void remove_1_fail(List<Integer> inputList, int capacity){
    final CircularQueue<Integer> ring_buffer = new CircularQueue<>(capacity);
    for (int val : inputList) {
      ring_buffer.add(val);
    }

    for (int i = 0; i < inputList.size(); i++) {
      ring_buffer.remove();
    }

    final Exception e = assertThrows(NoSuchElementException.class, ring_buffer::remove);
    assertEquals(CircularQueue.QUEUE_EMPTY, e.getMessage());
  }

  static Stream<Arguments> provideLargeListOfElementsToAdd() {
    return Stream.of(
      Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14), 8),
      Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19), 9),
      Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21), 5)
    );
  }

  @ParameterizedTest
  @MethodSource("provideLargeListOfElementsToAdd")
  @DisplayName("add and remove many valid number of valid elements to and from buffer")
  void add_remove_1(List<Integer> inputList, int capacity){
    final CircularQueue<Integer> ring_buffer = new CircularQueue<>(capacity);

    for (int val : inputList) {
      ring_buffer.add(val);
      ring_buffer.remove();
    }

    assertTrue(ring_buffer.isEmpty());
  }

  @ParameterizedTest
  @MethodSource("provideListOfElementsToAdd")
  @DisplayName("peek at last added element in buffer")
  void element_1(List<Integer> inputList, int capacity){
    final CircularQueue<Integer> ring_buffer = new CircularQueue<>(capacity);
    for (int val : inputList) {
      ring_buffer.add(val);
    }

    assertAll("group all add assertions"
      , () -> assertNotNull(ring_buffer)
      , () -> assertFalse(ring_buffer.isEmpty())
      , () -> assertEquals(inputList.getFirst(), ring_buffer.element())
    );
  }
}
