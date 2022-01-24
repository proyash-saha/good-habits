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

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import comp3350.goodhabits.Logic.QuoteManager;
import comp3350.goodhabits.Presentation.ProfileInputActivity;
import comp3350.goodhabits.Presentation.SettingsActivity;
import comp3350.goodhabits.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SettingsST {
    @Rule
    public ActivityTestRule<ProfileInputActivity> activityTestRule = new ActivityTestRule<>(ProfileInputActivity.class);
    QuoteManager quoteManager;

    @Before
    public void setUp(){
        activityTestRule.finishActivity();
        quoteManager = new QuoteManager(getApplicationContext());
        quoteManager.testMode();    // To set one particular quote on the Home screen every time the test is fired
        SystemTestUtils.cleanUpProfileDB();
        SystemTestUtils.cleanUpHabitDB();
        SystemTestUtils.setProfileDB("Test", "test@gmail.com");  // to by-pass Profile input
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void toggleQuote(){
        Intents.init();
        onView(withText("This quote is for System Testing - devTeam")).check(matches(isDisplayed()));   // Checking if the particular quote is present on the Home screen
        onView(withId(R.id.settings_button)).perform(click());   // Clicking the Settings button on the Home screen
        intended(hasComponent(SettingsActivity.class.getName()));   // Checking if we are in the SettingsActivity screen now
        onView(withText("Show quotes on home screen")).check(matches(isDisplayed()));   // Checking if the setting label is right
        onView(withId(R.id.quote_switch)).perform(click());     // Toggling the switch on the Settings screen
        pressBack();    // Going back to Home screen
        onView(withText("This quote is for System Testing - devTeam")).check(doesNotExist());   // Checking if the particular quote is now hidden on the Home screen
        Intents.release();
    }

    @After
    public void tearDown(){
        SystemTestUtils.resetSharedPreferences();   //  To update shared preferences to its default values after test is done
        SystemTestUtils.cleanUpProfileDB();
        SystemTestUtils.cleanUpHabitDB();
        quoteManager.notTestMode();
    }
}
