package com.genius.bitSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BitValidationTest {

    @Test
    public void bitValidation() {
        BitValidation bitValidation = new BitValidation();
        Assertions.assertTrue(bitValidation.isAllOn(100, List.of(
                new FromTo(0, 9),
                new FromTo(10, 19),
                new FromTo(20, 29),
                new FromTo(30, 99)
        )));
    }
}