package comp3350.goodhabits.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Logic.Notifier;
import comp3350.goodhabits.Logic.ProfileManager;
import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.Objects.Profile;
import comp3350.goodhabits.Persistence.SQLite.HabitSQLite;
import comp3350.goodhabits.Persistence.SQLite.ProfileSQLite;
import comp3350.goodhabits.Persistence.Stubs.HabitStorage;
import comp3350.goodhabits.Persistence.Stubs.ProfileStorage;
import comp3350.goodhabits.R;

public class ProfileInputActivity extends AppCompatActivity {

    private EditText profileName;
    private EditText profileEmail;

    @Override
    protected void onResume() {
        super.onResume();

        if(Notifier.getContext() == null)
            Notifier.setNotifier(ProfileInputActivity.this);

        if(HabitManager.getDB() == null){
            // Manager for Persistent Habit Storage
            HabitManager.createDB(new HabitSQLite(this));

            // Manager for Non-Persistent Habit Storage
            // HabitManager.createDB(new HabitStorage());
        }

        if(ProfileManager.getDB() == null){
            // Manager for Persistent Profile Storage
            ProfileManager.createDB(new ProfileSQLite(this));

            // Manager for Non-Persistent Profile Storage
            // ProfileManager.createDB(new ProfileStorage());
        }

        if(HabitManager.getHabitListSize() == 0){
            HabitManager.addHabit(new Habit(1,"Quit Smoking", false, "Smoking causes Cancer.", 11, 30, "13/03/2020", "18/05/2020", 15));
            HabitManager.addHabit(new Habit(2,"Drink Water", true, "Need to hydrate my body.", 10, 30, "13/03/2020", "18/05/2020", 34));
            HabitManager.addHabit(new Habit(3,"Do Yoga", true, "Need to stay fit.", 8, 0, "01/04/2021", "06/06/2021", 2));
        }

        // If profileIsSet is true then fills profile with fake data and goes directly to Main Screen
        if(ProfileManager.getProfileStorage() != null){
            moveToMainActivity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_input);

        // Title Bar text for this Activity
        setTitle("Create a Profile");

        // Extracting the profileName given by the user
        profileName = (EditText)findViewById(R.id.name_input);

        // Extracting the profileEmail given by the user
        profileEmail = (EditText)findViewById(R.id.email_input);

        // Button that validates Profile info, after being clicked
        Button done = (Button) findViewById(R.id.submit_profile);
        done.setOnClickListener(v -> validateForm());
    }

    // Helper function to move to Main Activity
    public void moveToMainActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    // Function to validate the Profile form
    public void validateForm(){
        boolean pass = true;

        String name = profileName.getText().toString();
        String email = profileEmail.getText().toString();

        // Fire a toast message if name field is empty
        if(name.equals("") || email.equals("")){
            Toast.makeText(this, "Both Name and Email are required!", Toast.LENGTH_SHORT).show();
            pass = false;
        }

        // If all fields are filled, then store the Profile and go to Main Screen
        if(pass){
            Profile profile = new Profile(name, email);
            ProfileManager.addToProfileStorage(profile);
            moveToMainActivity();
        }
    }
}
