package com.example.goodhabit;
import org.junit.Test;
import comp3350.goodhabits.Objects.Profile;
import static org.junit.Assert.assertEquals;

public class ProfileTest {
    @Test
    public void checkGetName() {
        Profile profile = new Profile("3350","3350@umanitoba.ca");
        assertEquals("3350",profile.getName());

    }

    @Test
    public void checkGetEmail() {
        Profile profile = new Profile("3350","3350@umanitoba.ca");
        assertEquals("3350@umanitoba.ca",profile.getEmail());

    }

}
