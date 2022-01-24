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
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import comp3350.goodhabits.Presentation.AllHabitsActivity;
import comp3350.goodhabits.Presentation.ProfileInputActivity;
import comp3350.goodhabits.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DeleteHabitST {
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
    public void deleteHabit(){
        Intents.init();
        onView(withId(R.id.all_habits_button)).perform(click());    // Clicking on the All Habits button from the Home screen
        intended(hasComponent(AllHabitsActivity.class.getName()));  // Making sure we are in the AllHabitsActivity now
        onView(withText("Quit Smoking")).check(matches(isDisplayed()));  // Checking if Quit Smoking habit is in the list
        onView(withText("Drink Water")).check(matches(isDisplayed()));  // Checking if Drink Water habit is in the list
        onView(withText("Do Yoga")).check(matches(isDisplayed()));  // Checking if Do Yoga habit is in the list
        onView(withText("Drink Water")).perform(longClick());   // Long click on Drink Water habit brings up a dialog to delete a habit
        onView(withText("YES")).perform(click());   // Clicking YES to delete the habit from the list
        onView(withText("Drink Water")).check(doesNotExist());  // Checking if the deleted habit still exists in the list
        Intents.release();
    }

    @After
    public void tearDown(){
        SystemTestUtils.cleanUpProfileDB();
        SystemTestUtils.cleanUpHabitDB();
    }
}
