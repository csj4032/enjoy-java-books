package com.genius.src;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReaderTest {

    @Test
    public void fileReader() {
        try (BufferedReader bIn = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data/111-11-1111.dat")))) {
            String line;
            while ((line = bIn.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}