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
import comp3350.goodhabits.Presentation.AllHabitsActivity;
import comp3350.goodhabits.Presentation.ProfileActivity;
import comp3350.goodhabits.Presentation.ProfileInputActivity;
import comp3350.goodhabits.Presentation.SettingsActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import comp3350.goodhabits.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeScreenButtonsST {
        @Rule
        public ActivityTestRule<ProfileInputActivity> activityTestRule = new ActivityTestRule<>(ProfileInputActivity.class);

        @Before
        public void setUp(){
                activityTestRule.finishActivity();
                SystemTestUtils.cleanUpProfileDB();
                SystemTestUtils.setProfileDB("Test", "test@gmail.com");  // to by-pass Profile input
                activityTestRule.launchActivity(new Intent());
        }

        @Test
        public void addButton(){
                Intents.init();
                onView(withId(R.id.add_button)).perform(click());   // Clicking the Add button on the Home screen
                intended(hasComponent(AddActivity.class.getName()));   // Checking if we are in the AddActivity screen now
                Intents.release();
        }

        @Test
        public void settingButton(){
                Intents.init();
                onView(withId(R.id.settings_button)).perform(click());   // Clicking the Settings button on the Home screen
                intended(hasComponent(SettingsActivity.class.getName()));   // Checking if we are in the SettingsActivity screen now
                Intents.release();
        }

        @Test
        public void listButton(){
                Intents.init();
                onView(withId(R.id.all_habits_button)).perform(click());   // Clicking the AllHabits button on the Home screen
                intended(hasComponent(AllHabitsActivity.class.getName()));   // Checking if we are in the AllHabitsActivity screen now
                Intents.release();
        }

        @Test
        public void profileButton(){
                Intents.init();
                onView(withId(R.id.user_profile_button)).perform(click());   // Clicking the UserProfile button on the Home screen
                intended(hasComponent(ProfileActivity.class.getName()));   // Checking if we are in the ProfileActivity screen now
                Intents.release();
        }

        @After
        public void tearDown(){
                SystemTestUtils.cleanUpProfileDB();
        }
}
