package com.genius;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashMapExample {

    Map<String, Student> students = new HashMap<>();

    @Test
    public void hashMapTest() {
        Student s1 = new Student("12345-12", "Fred");
        Student s2 = new Student("98765-00", "Barney");
        Student s3 = new Student("71024-91", "Wilma");

        students.put(s1.getIdNo(), s1);
        students.put(s2.getIdNo(), s2);
        students.put(s3.getIdNo(), s3);

        String id = "98765-00";
        Assertions.assertEquals(students.get(id), s2);

        id = "00000-00";
        Assertions.assertNotEquals(students.get(id), s1);

        Assertions.assertArrayEquals(students.values().toArray(), List.of(s1, s2, s3).toArray());
    }
}
