package edu.rit.swen352.tdd.hard;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * MyList is a flexible-sized sequence of elements with no gaps.
 * All elements must be non-{@code null}.
 *
 * <p>
 * You must implement these features:
 * <ul>
 *   <li>constructor: a ctor that supplies an initial capacity</li>
 *   <li>{@code isEmpty()}: queries if the list is empty</li>
 *   <li>{@code size():int}: queries how many elements in the list</li>
 *   <li>{@code get(index):T}: returns the element at a specific index;
 *     throw {@link java.util.NoSuchElementException} if the index is outside the size of the list</li>
 *   <li>{@code add(element:T)}: add an element to the end of the list</li>
 *   <li>{@code remove(element:T)}: remove an element by index</li>
 *   <li>{@code forEach(Consumer)}: iterates over each element and executes the {@link java.util.function.Consumer} parameter</li>
 * </ul>
 *
 * @param <T> the type of elements in the list.
 */
public class MyList<T> {
    
    private int size = 0;
    private Object[] elements;


    public MyList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        elements = new Object[initialCapacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }    

    public int size() {
        return size;
    }

    public void add(T element) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }

        return (T) elements[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }

        T removed = (T) elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;

        size--;

        return removed;
    }

    public void forEach(Consumer<T> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept((T) elements[i]);
        }
    }
}

