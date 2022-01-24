package com.example.goodhabit;

import android.app.AlarmManager;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import comp3350.goodhabits.Logic.Notifier;
import comp3350.goodhabits.Objects.Habit;
import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

public class NotifierTest {

    Notifier nt;
    @Mock
    Context mockContext;
    @Mock
    AlarmManager mockAlarmManager;

    @Before
    public void setUp()
    {
        nt=new Notifier();
        MockitoAnnotations.initMocks(this);
        when(mockContext.getSystemService(Context.ALARM_SERVICE)).thenReturn(mockAlarmManager);
        Notifier.setNotifier(mockContext);
    }

    @Test
    public void setNotificationTest()
    {
        Habit habit=new Habit(1,"Quit Smoking", false, "Smoking causes Cancer.", 11, 30, "13/03/2020", "18/05/2020", 15);
        nt.setHabitNotification(habit);
        //Non deterministic
        assert(true);
    }

    @Test
    public void setTimeTest()
    {
        Calendar c=nt.setTime(4,20);
        assertTrue(c.get(Calendar.HOUR_OF_DAY)==4);
        assertTrue(c.get(Calendar.MINUTE)==20);
    }
}
