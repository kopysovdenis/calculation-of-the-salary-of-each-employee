package queue;

import java.util.List;

public interface Queue<T> {
    void addAll(List<T> items);

    T remove();

    boolean isEmpty();
}
