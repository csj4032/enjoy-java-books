package chapter09.item62;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ThreadLocal Test")
public class ThreadLocalTest {

    @Test
    @DisplayName("ThreadLocal Static")
    public void threadLocalStatic() {
        Runnable runnable = () -> {
            ThreadLocalStatic.Key key = ThreadLocalStatic.getKey();
            ThreadLocalStatic.set(key, System.nanoTime());
            Object value = ThreadLocalStatic.get(key);
            System.out.println(value);
        };
        Thread thread1 = new Thread(runnable);
        thread1.setName("thread1");
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.setName("thread2");
        thread2.start();
        Thread thread3 = new Thread(runnable);
        thread3.setName("thread3");
        thread3.start();

    }

    @Test
    @DisplayName("ThreadLocal Instance")
    public void threadLocalInstance() {
        Runnable runnable = () -> {
            ThreadLocalInstance threadLocalInstance = new ThreadLocalInstance<Long>();
            threadLocalInstance.set(System.nanoTime());
            System.out.println(threadLocalInstance.get());
        };
        Thread thread1 = new Thread(runnable);
        thread1.setName("thread1");
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.setName("thread2");
        thread2.start();
        Thread thread3 = new Thread(runnable);
        thread3.setName("thread3");
        thread3.start();

    }
}
