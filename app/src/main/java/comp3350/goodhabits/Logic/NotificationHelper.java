package comp3350.goodhabits.Logic;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import comp3350.goodhabits.Presentation.AllHabitsActivity;
import comp3350.goodhabits.R;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper{
    //Id of the notification channel
    public final String habitChannelID = "HabitChannel";

    //Name of the notification channel
    public final String habitChannelName ="Habit Notification";

    //A Notifier Variable used to handle the notifications
    public NotificationManager nManager;

    //Constructor
    public NotificationHelper(Context context)
    {
        super(context);
        //Check android version to determine if channels need to be created
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            createNotificationChannel();
        }
    }


    //This method initializes and returns the Notifier
    public NotificationManager getManager()
    {
        //Initialize nManager if it has not been initialized
        if(nManager == null)
        {
            nManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return nManager;
    }

    //This method creates a notification channel required for android version greater than equal "oreo"
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel()
    {
        //Create Notification channel for Habit and add features
        NotificationChannel habitChannel = new NotificationChannel(habitChannelID,habitChannelName, NotificationManager.IMPORTANCE_HIGH);
        habitChannel.enableLights(true);
        habitChannel.enableVibration(true);
        habitChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(habitChannel);

    }

    //This method creates a notification for a Habit Reminder
    public NotificationCompat.Builder createHabitNotification(String habitName, String habitMsg)
    {
        //Create a pending intent which will be used to open AllHabitsActivity when the notification is tapped
        Intent intent=new Intent(this, AllHabitsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(getApplicationContext(),habitChannelID)
                .setContentTitle(habitName)
                .setContentText(habitMsg)
                .setSmallIcon(R.drawable.ic_habit)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }
}
