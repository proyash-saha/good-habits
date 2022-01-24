package com.example.goodhabit;
import org.junit.Test;

import comp3350.goodhabits.Objects.Habit;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HabitTest {
    @Test
    public void checkGetId() {

        Habit habit = new Habit(222, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        assertEquals(222, habit.getId());
   }

    @Test
    public void checkGetHabitName() {

        Habit habit = new Habit(223, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        assertEquals("Code early in the morning",habit.getHabitName());
    }

    @Test
    public void checkGetHabitType() {

        Habit habit = new Habit(224, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        assertTrue(habit.getHabitType());
    }

    @Test
    public void checkGetHabitMsg() {

        Habit habit = new Habit(225, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        assertEquals("Coding early morning increases productivity.",habit.getHabitMsg());
    }

    @Test
    public void checkGetHour() {

        Habit habit = new Habit(226, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        assertEquals(6,habit.getHour());
    }

    @Test
    public void checkGetMinute() {

        Habit habit = new Habit(227, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        assertEquals(30,habit.getMinute());
    }

    @Test
    public void checkGetStartDate() {

        Habit habit = new Habit(227, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        assertEquals("01/03/2021",habit.getStartDate());
    }

    @Test
    public void checkGetEndDate() {

        Habit habit = new Habit(227, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        assertEquals("05, 05,2021",habit.getEndDate());
    }

    @Test
    public void checkGetDaysCheckedIn() {

        Habit habit = new Habit(227, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        assertEquals(10,habit.getDaysCheckedIn());
    }
    @Test
    public void checkIncreaseCheckIn() {

        Habit habit = new Habit(227, "Code early in the morning", true, "Coding early morning increases productivity.", 6, 30, "01/03/2021", "05, 05,2021", 10);
        habit.increaseCheckIn();

        assertEquals(11,habit.getDaysCheckedIn());
    }

}
