package com.example.goodhabit.SystemTests;

import android.content.Context;
import android.content.SharedPreferences;

import comp3350.goodhabits.Logic.HabitManager;
import comp3350.goodhabits.Logic.ProfileManager;
import comp3350.goodhabits.Objects.Profile;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class SystemTestUtils {

    public static void cleanUpProfileDB(){
        ProfileManager.makeProfileEmpty();
    }

    public static void  setProfileDB(String name, String email){
        ProfileManager.addToProfileStorage(new Profile(name, email));
    }

    public static void cleanUpHabitDB(){
        HabitManager.makeHabitListEmpty();
    }

    public static void resetSharedPreferences(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("QuoteSharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("state", "on");
        myEdit.apply();
    }
}
