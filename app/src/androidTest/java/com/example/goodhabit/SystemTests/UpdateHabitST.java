package com.example.goodhabit.SystemTests;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import comp3350.goodhabits.Presentation.AllHabitsActivity;
import comp3350.goodhabits.Presentation.DetailActivity;
import comp3350.goodhabits.Presentation.ProfileInputActivity;
import comp3350.goodhabits.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UpdateHabitST {
    @Rule
    public ActivityTestRule<ProfileInputActivity> activityTestRule = new ActivityTestRule<>(ProfileInputActivity.class);

    @Before
    public void setUp(){
        activityTestRule.finishActivity();
        SystemTestUtils.cleanUpProfileDB();
        SystemTestUtils.cleanUpHabitDB();
        SystemTestUtils.setProfileDB("Test", "test@gmail.com");  // to by-pass Profile input
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void updateTime(){
        Intents.init();
        onView(withId(R.id.all_habits_button)).perform(click());   // Clicking the AllHabits button on the Home screen
        intended(hasComponent(AllHabitsActivity.class.getName()));   // Checking if we are in the AllHabitsActivity screen now
        onView(withText("Drink Water")).perform(click());   // Clicking on the Drink Water habit
        intended(hasComponent(DetailActivity.class.getName()));  // Checking if we are in the DetailActivity screen of Drink Water habit
        onView(withText("10:30 AM")).check(matches(isDisplayed()));   // Checking if the original notification time of Drink Water habit is displayed
        onView(withId(R.id.dv_change_time_button)).perform(click());    // Opening TimePicker
        onView(withText("OK")).perform(click());    // Clicking OK to set the new time
        onView(withText("10:30 AM")).check(doesNotExist());  // Checking if the original notification time of Drink Water habit is changed
        Intents.release();
    }

    @Test
    public void updateCheckIn(){
        Intents.init();
        onView(withId(R.id.all_habits_button)).perform(click());   // Clicking the AllHabits button on the Home screen
        intended(hasComponent(AllHabitsActivity.class.getName()));   // Checking if we are in the AllHabitsActivity screen now
        onView(withText("Drink Water")).perform(click());   // Clicking on the Drink Water habit
        intended(hasComponent(DetailActivity.class.getName()));  // Checking if we are in the DetailActivity screen of Drink Water habit
        onView(withText("34")).check(matches(isDisplayed()));   // Checking if the original check in number of Drink Water habit is displayed
        onView(withId(R.id.dv_check_in_button)).perform(click());   // Clicking the CHECK IN button to increase the number of days checked in
        onView(withText("35")).check(matches(isDisplayed()));// Checking if the original check in number of Drink Water habit is changed
        Intents.release();
    }

    @After
    public void tearDown(){
        SystemTestUtils.cleanUpProfileDB();
        SystemTestUtils.cleanUpHabitDB();
    }
}
