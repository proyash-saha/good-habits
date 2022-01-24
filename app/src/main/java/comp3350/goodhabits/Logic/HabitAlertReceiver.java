package comp3350.goodhabits.Logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class HabitAlertReceiver extends BroadcastReceiver {
    //Name tags
    private String habitName="Habit Name";
    private String habitMsg="Habit Msg";
    private String habitId="Habit ID";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper alarm = new NotificationHelper(context);
        //Get the information about the habit
        String title = intent.getStringExtra(habitName);
        String content = intent.getStringExtra(habitMsg);
        int id = intent.getIntExtra(habitId,0);
        //Check if the habit Exists
        if(HabitManager.checkHabit(id))
        {
            NotificationCompat.Builder nb = alarm.createHabitNotification(title,content);
            alarm.getManager().notify(id,nb.build());
        }
    }
}