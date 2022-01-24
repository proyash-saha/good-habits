package comp3350.goodhabits.Presentation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import comp3350.goodhabits.R;

public class SettingsActivity extends AppCompatActivity {
    // Storing data into SharedPreferences
    SharedPreferences sharedPreferences;
    SwitchCompat quoteSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Title Bar text for this Activity
        setTitle("Settings");

        // Tapping the back button on the Action Bar takes you to Home Screen
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        sharedPreferences = getSharedPreferences("QuoteSharedPref", Context.MODE_PRIVATE);
        quoteSwitch = (SwitchCompat) findViewById(R.id.quote_switch);

        String state = sharedPreferences.getString("state", "");
        quoteSwitch.setChecked(state.equals("") || state.equals("on"));

        quoteSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Creating an Editor object to edit(write to the file)
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            if(isChecked){
                myEdit.putString("state", "on");
            }
            else {
                myEdit.putString("state", "off");
            }
            myEdit.apply();
        });
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
