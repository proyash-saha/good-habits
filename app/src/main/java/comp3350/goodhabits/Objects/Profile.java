package comp3350.goodhabits.Objects;

// This is a Class for the Profile object of the user
public class Profile {
    private final String name; // User's name
    private final String email; // User's email

    public Profile(String name, String email){
        this.name = name;
        this.email = email;
    }

    // Returns the user's name
    public String getName(){
        return this.name;
    }

    // Returns the user's email
    public String getEmail(){
        return this.email;
    }
}
