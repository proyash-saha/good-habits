package comp3350.goodhabits.Presentation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Logic.QuoteManager;
import comp3350.goodhabits.R;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    QuoteManager quoteManager;
    TextView quote;
    private String quoteText = "";
    private int count = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume(){
        super.onResume();

        quoteManager = new QuoteManager(this);
        if(count == 1) {
            quoteText = quoteManager.getQuote();
            count += 1;
        }

        sharedPreferences = getSharedPreferences("QuoteSharedPref", Context.MODE_PRIVATE);
        String state = sharedPreferences.getString("state", "");

        quote = (TextView) findViewById(R.id.quote);
        if(state.equals("off")){
            quote.setText("");
        }
         else{
            quote.setText(quoteText);
        }

        // Updating the Total Habits count
        TextView habitCount = (TextView) findViewById(R.id.h_count_view);
        habitCount.setText(Integer.toString(HabitManager.getHabitListSize()));

        TextView ghCount = (TextView) findViewById(R.id.gh_count_view);
        ghCount.setText(Integer.toString(HabitManager.getTotalNumGoodHabits()));

        TextView bhCount = (TextView) findViewById(R.id.bh_count_view);
        bhCount.setText(Integer.toString(HabitManager.getHabitListSize() - HabitManager.getTotalNumGoodHabits()));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // To remove the default Title Bar of this Activity
        try {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException e) {
            System.out.println("Title bar of main screen could not be removed.");
        }

        // Clicking this button takes you to the Settings Screen
        ImageButton settings = (ImageButton) findViewById(R.id.settings_button);
        settings.setOnClickListener(v -> openSettingsActivity());

        // Clicking this button takes you to the Add Habit Screen
        ImageButton add = (ImageButton) findViewById(R.id.add_button);
        add.setOnClickListener(v -> openAddActivity());

        // Clicking this button takes you to the Screen with all the Habits created
        ImageButton allHabits = (ImageButton) findViewById(R.id.all_habits_button);
        allHabits.setOnClickListener(v -> openAllHabitsActivity());

        // Clicking this button takes you to the User's Profile Screen
        ImageButton profile = (ImageButton) findViewById(R.id.user_profile_button);
        profile.setOnClickListener(v -> openProfileActivity());
    }

    public void openSettingsActivity(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openAddActivity(){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void openAllHabitsActivity(){
        Intent intent = new Intent(this, AllHabitsActivity.class);
        startActivity(intent);
    }

    public void openProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

}
