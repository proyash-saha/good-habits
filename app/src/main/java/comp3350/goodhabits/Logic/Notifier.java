package comp3350.goodhabits.Logic;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import comp3350.goodhabits.Objects.Habit;

public class Notifier {
    //Name Tags to pass to new Intent
    private static final String HABIT_NAME = "Habit Name";
    private static final String HABIT_MSG = "Habit Msg";
    private static final String HABIT_ID = "Habit ID";
    private static Context context = null;

    public static void setNotifier(Context c) {
        context = c;
    }

    public static Context getContext() {
        return context;
    }

    //This method sets up a repeating notification for a habit
    public static void setHabitNotification(Habit habit)
    {
        //Calendar instance used to set the time
        Calendar c = setTime(habit.getHour(), habit.getMinute());
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //Intent is created so that information about the habit can be sent to the broadcast receiver
        Intent intent = new Intent(context, HabitAlertReceiver.class);
        intent.putExtra(HABIT_NAME, habit.getHabitName());
        intent.putExtra(HABIT_MSG, habit.getHabitMsg());
        intent.putExtra(HABIT_ID, habit.getId());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, habit.getId(), intent, 0);
        //Set a repeating alarm
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }

    //This method cancels the notification set for a particular habit
    public static void cancelAlarm(int id)
    {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, HabitAlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent,0);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }


    //This method sets the time during which the notification will be active
    public static Calendar setTime(int hour, int min)
    {
        //Set the time for the notification
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hour);
        c.set(Calendar.MINUTE,min);
        c.set(Calendar.SECOND,0);
        if(c.before(Calendar.getInstance()))
        {
            c.add(Calendar.DATE, 1);
        }
        return c;
    }
}
