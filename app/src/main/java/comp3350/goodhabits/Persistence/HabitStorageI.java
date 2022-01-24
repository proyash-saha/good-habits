package comp3350.goodhabits.Persistence;

import comp3350.goodhabits.Objects.Habit;

import java.util.ArrayList;

public interface  HabitStorageI {
    ArrayList<Habit> getHabitList();

    boolean addHabit(Habit habit);

    boolean updateHabit(Habit habit);

    boolean deleteHabit(Habit habit);

    boolean deleteHabitByIndex(int index);

    int getHabitListSize();

    boolean makeHabitListEmpty();
}