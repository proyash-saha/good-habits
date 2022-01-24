package com.example.goodhabit;
import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Logic.ProfileManager;
import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.Objects.Profile;
import comp3350.goodhabits.Persistence.ProfileStorageI;
import comp3350.goodhabits.Persistence.Stubs.HabitStorage;
import comp3350.goodhabits.Persistence.Stubs.ProfileStorage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfileManagerTest {
    ProfileStorageI fakeProfileStorage;

    @Before
    public void setup()
    {
        fakeProfileStorage=new ProfileStorage();
        Profile profile=new Profile("Test","test@email.com");
        ProfileManager.createDB(fakeProfileStorage);
        ProfileManager.addToProfileStorage(profile);
    }
    @After
    public void tearDown()
    {
        ProfileManager.makeProfileEmpty();
    }

    @Test
    public void TestAddToProfileStorage()
    {
        Profile profile = new Profile("Admin","Admin@myumanitoba.ca");
        ProfileManager.addToProfileStorage(profile);
        assertEquals(ProfileManager.getProfileStorage().getName(), profile.getName());
    }

    @Test
    public void TestGetProfileStorage()
    {
        assertEquals(ProfileManager.getProfileStorage().getName(),"Test");
    }

    @Test
    public void TestGetDb()
    {
        assertNotNull(ProfileManager.getDB());
    }


}
