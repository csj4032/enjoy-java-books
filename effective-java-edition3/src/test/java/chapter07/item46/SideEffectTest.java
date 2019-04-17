package chapter07.item46;

import com.google.common.base.Functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.jooq.lambda.Agg.count;

public class SideEffectTest {

    private static List<String> list;
    private static Map<String, Long> map;

    @BeforeAll
    public static void setUp() {
        list = List.of("AA", "AA", "B", "B", "C", "C", "D", "AA", "AA", "B", "B", "E", "E", "E", "F", "G", "H", "I", "H");
        map = list.stream().collect(groupingBy(Functions.identity(), count()));
    }

    @Test
    public void effectTest() {
        var effect = new SideEffect();
        effect.effect();
    }

    @Test
    public void topTen() {
        List<String> sort = map.keySet().stream().sorted((m1, m2) -> map.get(m2).compareTo(map.get(m1))).limit(10).collect(toList());
        List<String> sort2 = map.keySet().stream().sorted(comparing(map::get).reversed()).limit(10).collect(toList());
        Assertions.assertEquals(sort, sort2);
    }
}
