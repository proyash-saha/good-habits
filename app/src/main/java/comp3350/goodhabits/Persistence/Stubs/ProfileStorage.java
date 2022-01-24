package comp3350.goodhabits.Persistence.Stubs;

import comp3350.goodhabits.Objects.Profile;
import comp3350.goodhabits.Persistence.ProfileStorageI;

// This Class acts as a storage for the User's profile information
public class ProfileStorage implements ProfileStorageI {
    // Non-persistence ArrayList to store the Habit object
    private static Profile userProfile = null;

    public ProfileStorage(){

    }

    // Function to add the User's profile to an ArrayList
    public boolean addToProfileStorage(Profile profile){
        userProfile = profile;
        return userProfile != null;
    }

    // Function to get the User's profile in the ArrayList
    public Profile getProfileStorage(){
        return userProfile;
    }

    public boolean makeProfileEmpty() {
        userProfile = null;
        return true;
    }
}
