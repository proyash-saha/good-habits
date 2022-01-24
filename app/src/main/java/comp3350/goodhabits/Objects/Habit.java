package comp3350.goodhabits.Objects;

import java.io.Serializable;

// This is a Class for the Habit object
public class Habit implements Serializable {
    private final int id;
    private final String name; // Name of Habit
    private final boolean type; // Type of Habit, If true then it is a "Good Habit" else it is a "Bad Habit"
    private final String msg; // A message that the user writes inorder to engage in an activity
    private int hour; // Used to handle the hour of the day for the Habit
    private int minute; // Used to handle the minute of the hour for the Habit
    private final String startDate;
    private final String endDate;
    private int daysCheckedIn;

    public Habit(int id, String name, boolean type, String msg, int hour, int minute, String startDate, String endDate, int daysCheckedIn)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.msg = msg;
        this.hour = hour;
        this.minute = minute;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysCheckedIn = daysCheckedIn;
    }

    public int getId() { return this.id; }

    public String getHabitName(){
        return this.name;
    }

    public boolean getHabitType() {
        return this.type;
    }

    public String getHabitMsg() {
        return this.msg;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public int getDaysCheckedIn() { return this.daysCheckedIn; }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public void increaseCheckIn(){
        this.daysCheckedIn += 1;
    }
}
