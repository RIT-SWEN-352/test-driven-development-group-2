package edu.rit.swen352.tdd.hard;

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

    public MyList(int initialCapacity) {
    }

    public boolean isEmpty() {
        assert false : "not yet implemented";
        return false;
    }    
}

