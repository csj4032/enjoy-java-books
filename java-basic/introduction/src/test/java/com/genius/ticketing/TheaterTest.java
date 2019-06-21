package com.genius.ticketing;

import org.junit.jupiter.api.Test;

public class TheaterTest {

    @Test
    public void createTest() {
        Theater theater = new Theater();
    }

    @Test
    public void enterTest() {
        Ticket ticket = new Ticket();
        Theater theater = new Theater();
        theater.enter();
    }
}