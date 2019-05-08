package chapter09.item62;

public class ThreadLocalInstance<T> {

    private T value;

    public ThreadLocalInstance() {

    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
