package comp3350.goodhabits.Presentation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import comp3350.goodhabits.Logic.ProfileManager;
import comp3350.goodhabits.Logic.RatingManager;
import comp3350.goodhabits.Objects.Profile;
import comp3350.goodhabits.R;

public class ProfileActivity extends AppCompatActivity {
    Profile profile;
    RatingBar ratingBar;
    TextView ratingNum;
    RatingManager ratingManager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Title Bar text for this Activity
        setTitle("Profile");

        // Tapping the back button on the Action Bar takes you to Home Screen
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ratingManager = new RatingManager();

        ratingBar = (RatingBar) findViewById(R.id.rating_bar); // initiate a rating bar
        ratingBar.setRating(ratingManager.getRating());

        ratingNum = (TextView) findViewById(R.id.rating_num);
        ratingNum.setText(String.valueOf(ratingBar.getRating()));

        profile = ProfileManager.getProfileStorage(); // getting the non-persistent profile info
        String[] profileInfo = {profile.getName(), profile.getEmail()}; // putting the info in a String array
        fillProfileActivity(profileInfo);
    }

    // Function to populate all the Name and Email TextViews in this Activity
    public void fillProfileActivity(String [] profileInfo){
        TextView name = (TextView)findViewById(R.id.name_view);
        name.setText(profileInfo[0]);

        TextView email = (TextView)findViewById(R.id.email_view);
        email.setText(profileInfo[1]);
    }

    // Delegate function that recognises the tap on back button of this Activity
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
