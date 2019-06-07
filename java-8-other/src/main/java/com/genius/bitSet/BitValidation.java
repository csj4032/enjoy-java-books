package com.genius.bitSet;

import java.util.BitSet;
import java.util.List;

public class BitValidation {

    public boolean isAllOn(int len, List<FromTo> fromTos) {
        BitSet bitSet = new BitSet();
        for (FromTo fromTo : fromTos) {
            for (int j = fromTo.getFrom(); j <= fromTo.getTo(); j++) {
                bitSet.flip(j);
            }
        }
        return bitSet.cardinality() == len;
    }
}