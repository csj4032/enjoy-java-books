package chapter07.item47;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubListTest {

    @Test
    public void subList() {
        Stream<List<String>> subLists = SubLists.of(List.of("A", "B", "C"));
        Assertions.assertEquals(subLists.collect(Collectors.toList()), List.of(List.of(), List.of("A"), List.of("A", "B"), List.of("B"), List.of("A", "B", "C"), List.of("B", "C"), List.of("C")));

        // prefixes
        Assertions.assertEquals(List.of("A"), List.of("A", "B", "C").subList(0, 1));
        Assertions.assertEquals(List.of("A", "B"), List.of("A", "B", "C").subList(0, 2));
        Assertions.assertEquals(List.of("A", "B", "C"), List.of("A", "B", "C").subList(0, 3));

        // suffixes
        Assertions.assertEquals(List.of("A"), List.of("A").subList(0, 1));

        Assertions.assertEquals(List.of("A", "B"), List.of("A", "B").subList(0, 2));
        Assertions.assertEquals(List.of("B"), List.of("A", "B").subList(1, 2));

        Assertions.assertEquals(List.of("A", "B", "C"), List.of("A", "B", "C").subList(0, 3));
        Assertions.assertEquals(List.of("B", "C"), List.of("A", "B", "C").subList(1, 3));
        Assertions.assertEquals(List.of("C"), List.of("A", "B", "C").subList(2, 3));
    }
}