package com.example.goodhabit;

import comp3350.goodhabits.Logic.TimeManager;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {
    @Test
    public void checkTimeIn12Hr() {
        TimeManager time = new TimeManager();
        String result = time.getTime(0, 0);
        assertEquals("12:00 AM", result);
    }
}
