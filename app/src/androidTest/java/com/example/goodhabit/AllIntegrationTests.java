package com.example.goodhabit;

import com.example.goodhabit.IntegrationTests.HabitDatabaseIT;
import com.example.goodhabit.IntegrationTests.ProfileDatabaseIT;
import com.example.goodhabit.IntegrationTests.RatingIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    HabitDatabaseIT.class,
    ProfileDatabaseIT.class,
    RatingIT.class
})
public class AllIntegrationTests {

}
