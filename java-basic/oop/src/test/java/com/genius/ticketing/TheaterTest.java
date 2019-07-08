package com.genius.ticketing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TheaterTest {

    @Test
    @Order(1)
    @DisplayName("극장 객체 생성")
    public void createTest() {
        Theater theater = new Theater();
    }

    @Test
    @Order(2)
    @DisplayName("관계 입장 테스트")
    public void enterTest() {
        Theater theater = new Theater(0, 100);
        Audience audience = new Audience(10000);
        theater.enter(audience);
        Assertions.assertEquals(100, theater.getAmount());
        Assertions.assertEquals(9900, audience.getAmount());
    }
}