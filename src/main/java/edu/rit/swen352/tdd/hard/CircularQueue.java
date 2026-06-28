package edu.rit.swen352.tdd.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A fixed-sized, generic, FIFO Queue that uses modulo indexes to “circle around” around
 * the end of the array.  All elements must be non-{@code null}.
 *
 * <p>
 *   If you need help with the implementation, see:
 *   <a href='https://en.wikipedia.org/wiki/Circular_buffer'>Circular Buffer</a> (Wikipedia).
 * </p>
 *
 * <p>
 * You must implement these features:
 * <ul>
 *   <li>constructor:
 *     <ul>
 *       <li>a ctor that supplies the capacity, as an {@code int}</li>
 *       <li>and a no-arg ctor that defaults the capacity to 16</li>
 *       <li>NFR: once set, the capacity must be fixed</li>
 *     </ul>
 *   </li>
 *   <li>{@code getCapacity()}: returns the fixed capacity of the queue</li>
 *   <li>{@code isEmpty()}: queries if the queue is empty</li>
 *   <li>{@code add(element:T)}:
 *     <ul>
 *       <li>inserts an element at the end of the queue if it is not full</li>
 *       <li>throws {@link IllegalStateException} if the queue is full</li>
 *     </ul>
 *   </li>
 *   <li>{@code remove(element:T)}:
 *     <ul>
 *       <li>removes an element from the front of the queue if it is not empty</li>
 *       <li>throws {@link NoSuchElementException} if the queue is empty</li>
 *     </ul>
 *   </li>
 *   <li>{@code element():T}:
 *     <ul>
 *       <li>retrieves, but does not remove, an element from the front of the queue if it is not empty</li>
 *       <li>throws {@link NoSuchElementException} if the queue is empty</li>
 *     </ul>
 *   </li>
 *   <li>NFR: you must ensure that the size of the internal array never changes</li>
 *   <li>NFR: the queue must not keep references to any removed element</li>
 * </ul>
 *
 * @param <T> the type of elements in the queue.
 */
public class CircularQueue<T> {
  static final int DEFAULT_CAPACITY = 16;
  static final String BAD_CAPACITY = "Capacity should be greater than 0.";
  static final String QUEUE_FULL = "The CircularQueue is full, no more elements may be added until some are removed.";
  static final String QUEUE_EMPTY = "The CircularQueue is empty, no more elements may be removed until some are added.";

  private final int capacity;
  private int write_index = 0;
  private int read_index = 0;
  private final List<T> buffer;

  CircularQueue(int capacity) {
    if (capacity < 1){
      throw new IllegalArgumentException(BAD_CAPACITY);
    }
    this.capacity = capacity;
    this.buffer = new ArrayList<>(this.capacity);
  }

  CircularQueue() { this(DEFAULT_CAPACITY); }
  public int getCapacity() { return this.capacity; }
  public boolean isEmpty(){ return write_index == read_index; }

  public void add(T element) {
    if ((this.write_index + 1) % this.capacity == this.read_index){
      throw new IllegalStateException(QUEUE_FULL);
    }
    this.buffer.add(this.write_index, element);
    this.write_index = (this.write_index + 1) % this.capacity;
  }

  public T remove() {
    if (isEmpty()) {
      throw new NoSuchElementException(QUEUE_EMPTY);
    }
    T val = this.buffer.get(this.read_index);
    this.read_index = (this.read_index + 1) % this.capacity;
    return val;
  }
}
