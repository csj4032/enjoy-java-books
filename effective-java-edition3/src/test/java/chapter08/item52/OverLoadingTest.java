package chapter08.item52;

import org.junit.jupiter.api.Test;

public class OverLoadingTest {

    @Test
    private void overLoading() {
        OverLoading overLoading = new OverLoading();
        overLoading.method(1, 1, 1, 1);
        overLoading.method("a", "b", "c");
    }
}