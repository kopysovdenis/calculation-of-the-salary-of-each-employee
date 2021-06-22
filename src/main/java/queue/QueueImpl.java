package queue;

import java.util.ArrayList;
import java.util.List;

/**
 * queue implementation
 */
public class QueueImpl<T> implements Queue<T> {

    private ArrayList<T> list = new ArrayList<>();

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
}
