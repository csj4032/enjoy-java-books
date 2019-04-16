package chapter07.item46;

import org.junit.jupiter.api.Test;

public class SideEffectTest {

    @Test
    public void effectTest() {
        var effect = new SideEffect();
        effect.effect();
    }
}
