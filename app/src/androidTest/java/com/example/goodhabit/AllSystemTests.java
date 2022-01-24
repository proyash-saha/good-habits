package com.example.goodhabit;

import com.example.goodhabit.SystemTests.AddHabitST;
import com.example.goodhabit.SystemTests.CreateProfileST;
import com.example.goodhabit.SystemTests.DeleteHabitST;
import com.example.goodhabit.SystemTests.HomeScreenButtonsST;
import com.example.goodhabit.SystemTests.ProfileScreenST;
import com.example.goodhabit.SystemTests.SettingsST;
import com.example.goodhabit.SystemTests.UpdateHabitST;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddHabitST.class,
        CreateProfileST.class,
        DeleteHabitST.class,
        HomeScreenButtonsST.class,
        ProfileScreenST.class,
        SettingsST.class,
        UpdateHabitST.class
})

public class AllSystemTests {

}
