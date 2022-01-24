package com.example.goodhabit.IntegrationTests;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.content.Context;
import static org.junit.Assert.*;
import comp3350.goodhabits.Objects.Profile;
import comp3350.goodhabits.Persistence.SQLite.ProfileSQLite;
import comp3350.goodhabits.Logic.ProfileManager;

@RunWith(AndroidJUnit4.class)
public class ProfileDatabaseIT {

    @Before
    public void setUp(){
        Context profileContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ProfileManager.createDB(new ProfileSQLite(profileContext));
    }

    @Test
    public void addToProfileStorage(){
        Profile userProfile = new Profile("John Doe","johndoe@myumanitoba.ca");
        assertTrue(ProfileManager.addToProfileStorage(userProfile));
    }

    @Test
    public void getProfileStorage(){
        Profile userProfile = new Profile("John Doe","johndoe@myumanitoba.ca");
        ProfileManager.addToProfileStorage(userProfile);
        assertNotNull(ProfileManager.getProfileStorage());
    }

    @After
    public void teardown(){
        ProfileManager.makeProfileEmpty();
    }

}
