package com.genius.generic;

import java.util.LinkedList;
import java.util.List;

public class Generics {

    // Generics JDK 5.0
    public void generics() {
        List myList = new LinkedList();
        myList.add(new Integer(0));
        Integer x = (Integer) myList.iterator().next();

        List<Integer> myIntList = new LinkedList<>();
        myIntList.add(new Integer(0));
        Integer y = myIntList.iterator().next();
    }
}