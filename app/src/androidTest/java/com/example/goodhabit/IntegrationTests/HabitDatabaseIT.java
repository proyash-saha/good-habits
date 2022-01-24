package com.example.goodhabit.IntegrationTests;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Logic.Notifier;
import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.Persistence.SQLite.HabitSQLite;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class HabitDatabaseIT {

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Notifier.setNotifier(context);
        HabitManager.createDB(new HabitSQLite(context));
        HabitManager.addHabit(new Habit(1, "Quit Smoking", false, "Smoking causes Cancer.", 11, 30, "10/04/2020", "18/05/2020", 15));
        HabitManager.addHabit(new Habit(2, "Drink Water", true, "Need to hydrate my body.", 10, 30, "13/03/2020", "18/05/2020", 34));
        HabitManager.addHabit(new Habit(3, "Do Yoga", true, "Need to stay fit.", 8, 0, "01/04/2021", "06/06/2021", 2));

    }

    @Test
    public void habitList(){
        assertEquals(3, HabitManager.getHabitList().size());
    }

    @Test
    public void addHabit(){
        Habit habit = new Habit(4,"testing",true,"need to do test",8,10,"11/04/2021","06/06/2021", 2);
        assertTrue(HabitManager.addHabit(habit));
        assertTrue(HabitManager.checkHabit(4));
    }

    @Test
    public void updateHabit(){
        Habit habit = new Habit( 5,"testing upData",true,"need to do test upData",8,10,"11/04/2021","06/06/2021", 2);
        HabitManager.addHabit(habit);
        habit.setHour(3);
        assertTrue(HabitManager.updateHabit(habit));
        assertEquals(3, HabitManager.getHabitById(5).getHour());
    }

    @Test
    public void checkHabit(){
        Habit habit = new Habit( 6,"testing checkHabit",true,"need to do test checkHabit",8,10,"11/04/2021","06/06/2021", 2);
        HabitManager.addHabit(habit);
        assertTrue(HabitManager.checkHabit(6));
    }

    @Test
    public void allHabitNames(){
        String[] nameExpect = new String[HabitManager.getHabitList().size()];
        nameExpect[0] = "Quit Smoking";
        nameExpect[1] = "Drink Water";
        nameExpect[2] = "Do Yoga";

        try {
            assertArrayEquals(nameExpect, HabitManager.getAllHabitNames());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteHabit(){
        Habit habit = new Habit( 7,"testing delete",true,"need to do test delete",8,10,"11/04/2021","06/06/2021", 2);
        HabitManager.addHabit(habit);
        assertTrue(HabitManager.deleteHabit(habit));
    }

    @Test
    public void habitListSize(){
        int size = HabitManager.getHabitListSize();
        Habit habit = new Habit( 8,"testing getHabitListSize",true,"need to do test getHabitListSize",8,10,"11/04/2021","06/06/2021", 2);
        HabitManager.addHabit(habit);
        assertEquals(size+1, HabitManager.getHabitListSize());
    }

    @Test
    public void makeListEmpty(){
        assertTrue(HabitManager.makeHabitListEmpty());
    }

    @Test
    public void deleteHabitByIndex(){
        HabitManager.deleteHabitByIndex(2);
        assertEquals(2, HabitManager.getHabitListSize());
    }

    @Test
    public void getID(){
        assertEquals(4, HabitManager.getID());
    }

    @Test
    public void totalCheckins(){
        assertEquals(51, HabitManager.getTotalCheckins());
    }

    @Test
    public void totalDaysPassed(){
        assert(HabitManager.getTotalDaysPassed() >0);
    }

    @Test
    public void totalNumGoodHabits(){
        assertEquals(2, HabitManager.getTotalNumGoodHabits());
    }

    @Test
    public void habitById(){
        Habit habit = new Habit( 15,"testing getHabitById",true,"need to do test getHabitById",8,10,"11/04/2021","06/06/2021", 2);
        HabitManager.addHabit(habit);
        assertEquals(8, HabitManager.getHabitById(15).getHour());
    }

    @Test
    public void habitByIndex(){
        Habit habit = new Habit( 9,"testing getHabitByIndex",true,"need to do test getHabitByIndex",8,10,"12/04/2021","07/06/2021", 3);
        HabitManager.addHabit(habit);
        assertEquals(8, HabitManager.getHabitByIndex(3).getHour());
    }

    @After
    public void tearDown() {
        HabitManager.makeHabitListEmpty();
    }
}
