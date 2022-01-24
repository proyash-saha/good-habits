package com.example.goodhabit;
import android.app.AlarmManager;
import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Logic.Notifier;
import comp3350.goodhabits.Logic.RatingManager;
import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.Persistence.Stubs.HabitStorage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RatingManagerTest {

    RatingManager ratingManager;
    HabitManager hManager;
    HabitStorage fakeStorage;
    @Mock
    Context mockContext;
    @Mock
    AlarmManager mockAlarmManager;


    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        fakeStorage=new HabitStorage();
        when(mockContext.getSystemService(Context.ALARM_SERVICE)).thenReturn(mockAlarmManager);
        Notifier.setNotifier(mockContext);
        hManager.test=true;
        hManager.createDB(fakeStorage);
        hManager.addHabit(new Habit(1,"Quit Smoking", false, "Smoking causes Cancer.", 11, 30, "13/03/2020", "18/05/2020", 15));
        hManager.addHabit(new Habit(2,"Drink Water", true, "Need to hydrate my body.", 10, 30, "13/03/2020", "18/05/2020", 34));
        hManager.addHabit(new Habit(3,"Do Yoga", true, "Need to stay fit.", 8, 0, "01/04/2021", "06/06/2021", 2));
        ratingManager=new RatingManager();
    }

    @After
    public void tearDown()
    {
        hManager.makeHabitListEmpty();
    }

    @Test
    public void getRatingTest()
    {
        assertTrue((int)ratingManager.getRating()==1);
    }

}
