package comp3350.goodhabits.Presentation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import comp3350.goodhabits.Logic.HabitManager;

import comp3350.goodhabits.R;

public class AllHabitsActivity extends AppCompatActivity {

    private ArrayAdapter <String>  arrayAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_habits);

        Intent intent = new Intent(this, DetailActivity.class);
        ListView listView = (ListView) findViewById(R.id.listview);

        try {
            arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, HabitManager.getAllHabitNames());
            listView.setAdapter(arrayAdapter1);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // Show a toast message when a row in the list view is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                intent.putExtra("index", i);
                startActivity(intent);
            }
        });

        // When user long press a Habit, there are show an option to delete it
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(AllHabitsActivity.this)
                        .setTitle("Delete")
                        .setMessage("Do you want to delete this habit ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                HabitManager.deleteHabitByIndex(position);

                                //refresh detail page
                                arrayAdapter1.notifyDataSetChanged();

                                // startActivity(new Intent(AllHabitsActivity.this, AllHabitsActivity.class));
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });

        // Title Bar text for this Activity
        setTitle("All Habits");

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
}
