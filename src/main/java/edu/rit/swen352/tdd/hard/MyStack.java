package edu.rit.swen352.tdd.hard;

/**
 * MyStack is a fixed-size, LIFO (last-in-first-out) stack.
 * All elements must be non-{@code null}.
 *
 * <p>
 *   If you need help with the implementation, see:
 *   <a href="https://en.wikipedia.org/wiki/Stack_(abstract_data_type)">Stack ADT</a> (Wikipedia).
 * </p>
 *
 * <p>
 *   You must implement these features:
 *   <ul>
 *     <li>constructor:
 *       <ul>
 *         <li>a ctor that supplies the capacity, as an {@code int}</li>
 *         <li>and a no-arg ctor that defaults the capacity to 16</li>
 *         <li>NFR: once set, the capacity must be fixed</li>
 *       </ul>
 *     </li>
 *     <li>{@code getCapacity()}: returns the fixed capacity of the stack</li>
 *     <li>{@code isEmpty()}: queries if the stack is empty</li>
 *     <li>{@code size():int}: queries how many elements are in the stack</li>
 *     <li>{@code push(element:T)}:
 *       <ul>
 *         <li>add an element to the stack</li>
 *         <li>throws {@link IllegalStateException} if the stack is full</li>
 *       </ul>
 *     </li>
 *     <li>{@code pop():T}:
 *       <ul>
 *         <li>remove and return the top element of the stack</li>
 *         <li>throws {@link java.util.NoSuchElementException} if the stack is empty</li>
 *       </ul>
 *     </li>
 *     <li>{@code peek():T}:
 *       <ul>
 *         <li>return the top element of the stack; leave the stack unchanged</li>
 *         <li>throws {@link java.util.NoSuchElementException} if the stack is empty</li>
 *       </ul>
 *     </li>
 *   </ul>
 * </p>
 *
 * @param <T> the type of elements in the stack.
 */
public class MyStack<T> {

    private int capacity;
    private int size;
    private final Object[] elements;

    public MyStack(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.elements = new Object[capacity];
    }

    public MyStack() {
        this(16);
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(T element) {
        if (size == capacity) {
            throw new IllegalStateException("Stack is full");
        }

        if (element == null) {
            throw new NullPointerException("Stack cannot contain null elements");
        }

        elements[size] = element;
        size++;
    }

}
