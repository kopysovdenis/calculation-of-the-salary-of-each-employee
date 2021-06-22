package queue;

import java.util.List;

/**
 * The queue for traversing the employee structure in width
 */
public interface Queue<T> {
    void addAll(List<T> items);

    T remove();

    boolean isEmpty();
}
