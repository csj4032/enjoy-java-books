package chapter07.item46;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class SideEffect {

    final static AtomicInteger count = new AtomicInteger();

    public void effect() {
        var stream = Stream.<Integer>builder().add(1).add(2).add(3).build();
        var sum = stream.map(e -> {
            count.incrementAndGet();
            return e;
        }).reduce(0, Integer::sum);
        System.out.println(count + " " + sum);
    }

    public void isSideEffect(String file) {
        Map<String, Long> freq = new HashMap<>();
        // 수정 여지가 있음
        try (Stream<String> words = new Scanner(file).tokens()) {
            words.forEach(word -> freq.merge(word.toLowerCase(), 1L, Long::sum));
        }
    }

    public Map<String, Long> isNotSideEffect(String file) {
        try (Stream<String> words = new Scanner(file).tokens()) {
            return words.collect(groupingBy(String::toLowerCase, counting()));
        }
    }
}
