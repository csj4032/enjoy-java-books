package chapter09.item62;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalStatic {

    private static volatile Map<Key, Object> MAP = new ConcurrentHashMap<>();

    private ThreadLocalStatic() {

    }

    public static class Key {
        Key() {
        }
    }

    public static  Key getKey() {
        return new Key();
    }

    public static void set(Key key, Object value) {
        MAP.put(key, value);
    }

    public static Object get(Key key) {
        return MAP.get(key);
    }
}
