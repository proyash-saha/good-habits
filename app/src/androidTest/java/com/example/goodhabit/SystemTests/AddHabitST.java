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

import comp3350.goodhabits.Presentation.AddActivity;
import comp3350.goodhabits.Presentation.HomeActivity;
import comp3350.goodhabits.Presentation.ProfileInputActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import comp3350.goodhabits.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddHabitST {
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
    public void addHabit(){
        Intents.init();
        onView(withText("3")).check(matches(isDisplayed()));    // Total Habits count in Home Screen
        onView(withText("4")).check(doesNotExist());    // Making sure there are exactly 3 Habits
        onView(withId(R.id.add_button)).perform(click());   // Clicking the add button on Home Screen
        intended(hasComponent(AddActivity.class.getName()));    // Checking if we are in AddActivity now
        onView(withId(R.id.habit_name_input)).perform(typeText("System Test")); // Typing Habit name
        onView(withId(R.id.good_habit)).perform(click());   // Assigning Habit type
        onView(withId(R.id.habit_message_input)).perform(typeText("This message is for a System Test"));    // Typing Habit message
        onView(withId(R.id.time_picker)).perform(scrollTo()).perform(click());  // Opening TimePicker
        onView(withText("OK")).perform(click());    // Assigning Habit time
        onView(withId(R.id.submit_habit)).perform(click()); // Clicking the Add Button
        intended(hasComponent(HomeActivity.class.getName()));   // Checking if we are in HomeActivity now
        onView(withText("4")).check(matches(isDisplayed()));    // Total Habits count in Home Screen is now 3+1=4
        Intents.release();
    }

    @After
    public void tearDown(){
        SystemTestUtils.cleanUpProfileDB();
        SystemTestUtils.cleanUpHabitDB();
    }
}
