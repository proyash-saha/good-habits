package com.example.goodhabit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.Persistence.Stubs.HabitStorage;

public class HabitStorageTest {
    HabitStorage fakeHabitStorage;

    @Before
    public void setUp()
    {
        fakeHabitStorage=new HabitStorage();
        fakeHabitStorage.addHabit(new Habit(1,"Quit Smoking", false, "Smoking causes Cancer.", 11, 30, "13/03/2020", "18/05/2020", 15));
        fakeHabitStorage.addHabit(new Habit(2,"Drink Water", true, "Need to hydrate my body.", 10, 30, "13/03/2020", "18/05/2020", 34));
        fakeHabitStorage.addHabit(new Habit(3,"Do Yoga", true, "Need to stay fit.", 8, 0, "01/04/2021", "06/06/2021", 2));


    }

    @After
    public void tearDown()
    {
        fakeHabitStorage.makeHabitListEmpty();
    }

    @Test
    public void getHabitListTest()
    {
        ArrayList<Habit> list=fakeHabitStorage.getHabitList();
        assert (list.size()==3);
    }

    @Test
    public void addHabitTest()
    {
        Habit newHabit=new Habit(50,"Do Testing", true, "Any test is a good test", 11, 30, "13/03/2020", "18/05/2020", 15);
        fakeHabitStorage.addHabit(newHabit);
        assertNotNull(fakeHabitStorage.getHabitById(50));
    }

    @Test
    public void updateHabitTest()
    {
        Habit newHabit=new Habit(55,"Update Testing", true, "Any test is a good test", 11, 30, "13/03/2020", "18/05/2020", 15);
        fakeHabitStorage.addHabit(newHabit);
        newHabit.setHour(7);
        fakeHabitStorage.updateHabit(newHabit);
        assert (fakeHabitStorage.getHabitById(55).getHour()==7);
    }

    @Test
    public void deleteHabitTest()
    {
        Habit newHabit=new Habit(60,"Delete Testing", true, "Any test is a good test", 11, 30, "13/03/2020", "18/05/2020", 15);
        fakeHabitStorage.addHabit(newHabit);
        fakeHabitStorage.deleteHabit(newHabit);
        assertNull(fakeHabitStorage.getHabitById(60));
    }


    @Test
    public void getHabitListSizeTest()
    {
        assert (fakeHabitStorage.getHabitListSize()>0);
    }

    @Test
    public void deleteHabitByIndexTest()
    {
        int old=fakeHabitStorage.getHabitListSize();
        fakeHabitStorage.deleteHabitByIndex(0);
        int neww=fakeHabitStorage.getHabitListSize();
        assert(old>neww);
    }

    @Test
    public void getAllHabitNamesTest()
    {
        try{
            String[] list=fakeHabitStorage.getAllHabitNames();
            assert (list.length>0);
        }
        catch (Exception e)
        {
            e.toString();
        }
    }


}

