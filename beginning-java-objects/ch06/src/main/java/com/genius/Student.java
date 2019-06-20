package com.genius;

import lombok.Data;

@Data
public class Student {
    private final String idNo;
    private final String name;

    public Student(String name) {
        this("", name);
    }

    public Student(String idNo, String name) {
        this.idNo = idNo;
        this.name = name;
    }
}