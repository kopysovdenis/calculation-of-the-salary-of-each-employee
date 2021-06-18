package stack;

import java.util.List;

public interface Queue<T> {
    void add(T item);   // добавить элемент в конец очереди
    void addAll(List<T> items);
    T remove();// извлечение элемента из начала очереди

    boolean isEmpty();
}
