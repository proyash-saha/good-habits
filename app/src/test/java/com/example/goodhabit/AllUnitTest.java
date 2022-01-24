package com.example.goodhabit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DateTest.class,
        TimeTest.class,
        HabitTest.class,
        ProfileTest.class,
        ProfileManagerTest.class,
        HabitStorageTest.class,
        HabitSqlTest.class,
        HabitManagerTest.class,
        NotifierTest.class,
        RatingManagerTest.class
})
public class AllUnitTest {

}
