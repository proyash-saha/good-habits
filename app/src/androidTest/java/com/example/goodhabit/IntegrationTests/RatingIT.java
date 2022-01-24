package com.example.goodhabit.IntegrationTests;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Logic.Notifier;
import comp3350.goodhabits.Logic.RatingManager;
import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.Persistence.SQLite.HabitSQLite;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class RatingIT {
    RatingManager ratingManager;

    @Before
    public void setUp(){
        ratingManager = new RatingManager();
        Context habitContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Notifier.setNotifier(habitContext);
        HabitManager.createDB(new HabitSQLite(habitContext));
        HabitManager.addHabit(new Habit(1,"Quit Smoking", false, "Smoking causes Cancer.", 11, 30, "10/04/2020", "18/05/2020", 15));
        HabitManager.addHabit(new Habit(2,"Drink Water", true, "Need to hydrate my body.", 10, 30, "13/03/2020", "18/05/2020", 34));
        HabitManager.addHabit(new Habit(3,"Do Yoga", true, "Need to stay fit.", 8, 0, "01/04/2021", "06/06/2021", 2));
    }

    @Test
    public void getRating(){
        assertTrue(ratingManager.getRating() > 0);
     }

    @After
    public void tearDown() {
        HabitManager.makeHabitListEmpty();
    }
}
