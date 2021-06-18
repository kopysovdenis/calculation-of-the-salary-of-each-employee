package stack;

public interface Stack<T> {
    void push(T item);
    T pop();

    boolean isEmpty();
}
