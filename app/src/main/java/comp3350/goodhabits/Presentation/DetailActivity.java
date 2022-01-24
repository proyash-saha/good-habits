package comp3350.goodhabits.Presentation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import comp3350.goodhabits.Logic.DateManager;
import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Logic.Notifier;
import comp3350.goodhabits.Logic.TimeManager;
import comp3350.goodhabits.Logic.TimePickerFragment;
import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.R;

import java.text.ParseException;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    TextView name;
    TextView message;
    TextView notificationTime;
    TextView startDate;
    TextView endDate;
    TextView daysPassed;
    TextView checkins;
    Button changeTime;
    ProgressBar progressBar;
    Button checkInButton;
    View type;

    private final int DCI = 66; // Days Checked In
    private int days = 0;
    Habit habit;
    DateManager dateParser = new DateManager();
    TimeManager timeParser = new TimeManager();
    ArrayList<Habit> habitList = HabitManager.getHabitList();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getIntent().getExtras() != null){
            int index = (int) getIntent().getSerializableExtra("index");
            habit = habitList.get(index);

            name = (TextView) findViewById(R.id.dv_name);
            name.setText(habit.getHabitName());

            type = (View) findViewById(R.id.dv_type);
            if(habit.getHabitType())
                type.setBackgroundColor(Color.parseColor("#66BB6A"));
            else
                type.setBackgroundColor(Color.parseColor("#Ef5350"));

            message = (TextView) findViewById(R.id.dv_message);
            message.setText(habit.getHabitMsg());

            notificationTime = (TextView) findViewById(R.id.dv_notification_time);;
            notificationTime.setText(timeParser.getTime(habit.getHour(), habit.getMinute()));

            startDate = (TextView) findViewById(R.id.dv_start_date);
            startDate.setText(habit.getStartDate());

            endDate = (TextView) findViewById(R.id.dv_end_date);
            endDate.setText(habit.getEndDate());

            daysPassed = (TextView) findViewById(R.id.dv_days_passed);
            try {
                days = dateParser.getDaysPassed(habit.getStartDate(), dateParser.getTodaysDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(days >= DCI){
                System.out.println(days);
                daysPassed.setText(DCI+"+");
            }
            else{
                System.out.println(days);
                daysPassed.setText(String.valueOf(days));
            }

            checkins = (TextView) findViewById(R.id.dv_checkins);
            checkins.setText(String.valueOf(habit.getDaysCheckedIn()));

            changeTime = (Button) findViewById(R.id.dv_change_time_button);
            changeTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(),"Time Picker");
                }
            });

            progressBar = (ProgressBar)findViewById(R.id.rating_progress_bar);
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#FBC263"), android.graphics.PorterDuff.Mode.SRC_IN);
            progressBar.setProgress(habit.getDaysCheckedIn());

            checkInButton = (Button) findViewById(R.id.dv_check_in_button);
            checkInButton.setOnClickListener(v -> increaseCheckIn());
        }

        else{
            System.out.println("ERROR: The Habit object was not passed to the Detail View.");
        }

        // Title Bar text for this Activity
        setTitle("Detail");

        // Tapping the back button on the Action Bar takes you to Home Screen
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // Delegate function that recognises the tap on back button of this Activity
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void increaseCheckIn(){
        if (habit.getDaysCheckedIn() < DCI){
            if (habit.getDaysCheckedIn() < days) {
                habit.increaseCheckIn();
                HabitManager.updateHabit(habit);
                checkins.setText(String.valueOf(habit.getDaysCheckedIn()));
                progressBar.setProgress(habit.getDaysCheckedIn());
            }
            else {

                Toast.makeText(this,"Maximum check in for the days has been reached!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        habit.setHour(hour);
        habit.setMinute(minute);
        HabitManager.updateHabit(habit);
        notificationTime.setText(timeParser.getTime(habit.getHour(), habit.getMinute()));
    }
}
