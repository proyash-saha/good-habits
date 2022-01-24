package comp3350.goodhabits.Persistence.Stubs;

import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.Persistence.HabitStorageI;

import java.util.ArrayList;

// This Class acts as a Storage for all the Habits created
public class HabitStorage implements HabitStorageI {
    // Non-persistence ArrayList to store all the Habit objects
    private static ArrayList<Habit> habitStorage = new ArrayList<>();

    public HabitStorage(){

    }

    // Function to get the full list of Habits
    public ArrayList<Habit> getHabitList(){
        return habitStorage;
    }

    // Function to add a Habit to the ArrayList
    public boolean addHabit(Habit habit){
         return habitStorage.add(habit);
    }

    public boolean updateHabit(Habit habit) {
        boolean result = false;
        for(int i=0; i<habitStorage.size(); i++){
            if(habitStorage.get(i).getId() == habit.getId()){
                habitStorage.remove(i);
                habitStorage.add(i, habit);
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean deleteHabit(Habit habit) {
        boolean result = false;
        int id = habit.getId();
        for(int i=0; i<habitStorage.size(); i++){
            if(habitStorage.get(i).getId() == id) {
                habitStorage.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    public int getHabitListSize(){
        return habitStorage.size();
    }

    public boolean deleteHabitByIndex(int index){
        boolean result = false;
        if(habitStorage.remove(index) != null){
            result = true;
        }
        return result;
    }

    public String[] getAllHabitNames() throws Exception{
        if(habitStorage.size() == 0)
            throw new Exception("ERROR: HabitStorage is empty");
        String[] result = new String[habitStorage.size()];
        for(int i=0; i<habitStorage.size(); i++){
            result[i] = habitStorage.get(i).getHabitName();
        }
        return result;
    }

    public boolean makeHabitListEmpty(){
        habitStorage.clear();
        return true;
    }

    public Habit getHabitById(int id)
    {
        Habit habit=null;
        for(int i=0;i<habitStorage.size();i++)
        {
            if(habitStorage.get(i).getId()==id)
            {
                habit=habitStorage.get(i);
            }
        }
        return habit;

    }
}
