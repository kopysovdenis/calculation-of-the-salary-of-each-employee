package stack;

import java.util.List;

public interface Queue<T> {
    void add(T item);

    void addAll(List<T> items);

    T remove();

    boolean isEmpty();
}
