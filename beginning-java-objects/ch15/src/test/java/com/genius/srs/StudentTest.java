package com.genius.srs;

import com.genius.srs.Student;
import com.genius.srs.Transcript;

import org.junit.jupiter.api.Test;

public class StudentTest {

    @Test
    public void createStudent() {
        Student student = new Student("111-11-1111");
        Transcript transcript = student.getTranscript();
        transcript.display();
    }
}
