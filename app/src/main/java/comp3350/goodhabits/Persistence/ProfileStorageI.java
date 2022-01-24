package comp3350.goodhabits.Persistence;

import comp3350.goodhabits.Objects.Profile;

public interface ProfileStorageI {
    boolean addToProfileStorage(Profile profile);
    Profile getProfileStorage();
    boolean makeProfileEmpty();
}
