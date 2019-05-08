package chapter09.item60;

import java.util.Comparator;

public class NaturalOrder {

    private Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);


}
