package stack;

import java.util.ArrayList;
import java.util.List;

public class SimpleQueue<T> implements Queue<T> {

    private ArrayList<T> list = new ArrayList<>();

    @Override
    public void add(T item) {
        if (item != null)
            list.add(item);
    }

    @Override
    public void addAll(List<T> items) {
        if (items != null)
            list.addAll(items);
    }

    @Override
    public T remove() {
        return list.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(T item) {
        return list.contains(item);
    }
}
