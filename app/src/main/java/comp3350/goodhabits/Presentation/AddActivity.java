package comp3350.goodhabits.Presentation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import comp3350.goodhabits.Logic.DateManager;
import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Logic.TimeManager;
import comp3350.goodhabits.Logic.TimePickerFragment;
import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.R;


// This Class helps create a new Habit
public class AddActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private String name;
    private RadioGroup typeGroup;
    private RadioButton type;
    private String msg;
    private int hour = -1; // Initialized to -1
    private int minute = -1; // Initialized to -1

    TimeManager time = new TimeManager();
    DateManager dateParser = new DateManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Contexts of the form that need to be stored
        EditText habitName;
        EditText habitMsg;
        Button timeButton;
        Button addButton;

        // Title Bar text for this Activity
        setTitle("Add a Habit");

        // Tapping the back button on the Action Bar takes you to Home Screen
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Get the name of the Habit when it is Written
        habitName = findViewById(R.id.habit_name_input);

        // Tapping a RadioButton to determine type of Habit
        typeGroup = findViewById(R.id.habit_type_group);
        typeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                type = findViewById(id); // Assign the new selected button to type
            }
        });

        // When the User message is written
        habitMsg = findViewById(R.id.habit_message_input);

        // Tapping the Time Picker Button to select a time for the activity regarding the habit
        timeButton = findViewById(R.id.time_picker);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time Picker");
            }
        });

        // Tapping the "Submit" Button on the addHabit form
        addButton = findViewById(R.id.submit_habit);
        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                name = habitName.getText().toString(); // Get the name of the habit

                // Get the typeGroup selected (If the user does not click on the RadioButtons)
                int radioId = typeGroup.getCheckedRadioButtonId();
                type = findViewById(radioId);
                msg = habitMsg.getText().toString(); // Get the User message
                validateForm(); // Check for toast message

                // If any Toast message is NOT displayed
                if(!validateForm()) {
                    Habit newHabit = createHabit(name,type,msg,hour,minute); // Create a Habit
                    addHabit(newHabit); // Add the Habit to the list of Habits
                    startActivity(new Intent(AddActivity.this, HomeActivity.class)); // Go to the main screen
                }
            }
        });
    }

    // This Function finds the RadioButton that is selected by the user
    public void selectedButton(View v)
    {
        int radioId = typeGroup.getCheckedRadioButtonId();   // Get the ID of the selected button
        type = findViewById(radioId); // Assign it to our RadioButton Variable
    }

    // This Function displays the time selected and stores the time selected by the user
    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfHour) {
        // Show the time selected by the user in 12 hr format for better understanding
        TextView textView = (TextView)findViewById(R.id.time_picker_label);
        textView.setText(time.getTime(hourOfDay, minuteOfHour));

        // Store the time selected by the user in 24 hr format for ease of coding
        hour = hourOfDay;
        minute = minuteOfHour;
    }

    // Delegate function that recognises the tap on back button of this Activity
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // This function creates a new Habit
    public Habit createHabit(String hName, RadioButton hType, String hMsg, int hTime, int mTime) {
        // A boolean variable that is true for Good_Habit and false for Bad_Habit
        boolean boolType = hType.getId() == findViewById(R.id.good_habit).getId();
        String startDate = dateParser.getTodaysDate();
        String endDate ="";
        try {
            endDate = dateParser.getEndDate(startDate);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return new Habit(HabitManager.getID(), hName, boolType, hMsg, hTime, mTime, startDate, endDate, 0);
    }

    // This Function Adds a new Habit to the list of Habits
    public void addHabit(Habit habit)
    {
        HabitManager.addHabit(habit);
    }

    public boolean validateForm() {
        boolean toastFired = false;

        String errorMsg="";
        // Check if the user gave a Habit a name
        if(name == null || name.length() == 0)
        {
            errorMsg="Habit name is missing!";
            toastFired = true;
        }

        // Check if the user selected a type of Habit
        if(typeGroup.getCheckedRadioButtonId() == -1)
        {
            errorMsg="Habit type is missing!";
            toastFired = true;
        }

        // Check if the user selected a time
        if(hour == -1 && minute == -1)
        {
            errorMsg="Time is not chosen";
            toastFired = true;
        }

        if(toastFired)
        {
            Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
        }

        return toastFired;

    }


}
