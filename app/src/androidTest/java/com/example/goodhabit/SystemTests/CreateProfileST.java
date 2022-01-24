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
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import comp3350.goodhabits.Presentation.HomeActivity;
import comp3350.goodhabits.Presentation.ProfileInputActivity;
import comp3350.goodhabits.R;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateProfileST {
    @Rule
    public ActivityTestRule<ProfileInputActivity> activityTestRule = new ActivityTestRule<>(ProfileInputActivity.class);

    @Before
    public void setUp(){
        activityTestRule.finishActivity();
        SystemTestUtils.cleanUpProfileDB();
        SystemTestUtils.cleanUpHabitDB();
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void successProfileInput(){
        Intents.init();
        onView(withId(R.id.name_input)).perform(typeText("Test"));  // Typing the name
        onView(withId(R.id.email_input)).perform(typeText("test@gmail.com"));   // Typing the email
        onView(withId(R.id.submit_profile)).perform(click()); // Clicking the Done button
        intended(hasComponent(HomeActivity.class.getName()));   // Making sure we are in the Home Screen now
        Intents.release();
    }

    @Test
    public void failProfileInput(){
        Intents.init();
        onView(withId(R.id.name_input)).perform(typeText("Test"));  // Typing the name
        onView(withId(R.id.email_input)).perform(typeText(""));   // Leaving the email field empty
        onView(withId(R.id.submit_profile)).perform(click());   // Clicking the Done button
        onView(withText("Done")).check(matches(isDisplayed())); // Making sure we are still on the same screen
        Intents.release();
    }

    @After
    public void tearDown(){
        SystemTestUtils.cleanUpProfileDB();
        SystemTestUtils.cleanUpHabitDB();
    }
}
