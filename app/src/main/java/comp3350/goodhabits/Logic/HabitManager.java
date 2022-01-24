package comp3350.goodhabits.Logic;

import comp3350.goodhabits.Objects.Habit;
import comp3350.goodhabits.Persistence.HabitStorageI;

import java.text.ParseException;
import java.util.ArrayList;

public class HabitManager{
    public static boolean test=false;//Set to true while testing
    private static HabitStorageI habitStorage = null;

    public static void createDB(HabitStorageI db) {
        habitStorage = db;
    }

    public static HabitStorageI getDB(){
        return habitStorage;
    }

    public static ArrayList<Habit> getHabitList(){
        return habitStorage.getHabitList();
    }

    public static boolean addHabit(Habit habit){
        if(!test) {
            //Set the notification for the habit
            Notifier.setHabitNotification(habit);
        }
        return habitStorage.addHabit(habit);
    }
    
    public static boolean updateHabit(Habit habit)
    {
        if(!test) {
            //Cancel the notification for the old time
            Notifier.cancelAlarm(habit.getId());
            //Set a notification for the new time
            Notifier.setHabitNotification(habit);
        }
        return habitStorage.updateHabit(habit);
    }

    public static boolean checkHabit(int id)
    {
        boolean result = false;
        ArrayList<Habit> list = habitStorage.getHabitList();
        for(int i=0 ; i<list.size() ; i++)
        {
            if(list.get(i).getId()==id && !result)
            {
                result=true;
            }
        }
        return result;
    }

    public static String[] getAllHabitNames() throws Exception{
        ArrayList<Habit> habitList = habitStorage.getHabitList();
        if(habitList.size() == 0)
            throw new Exception("ERROR: HabitStorage is empty");
        String[] result = new String[habitList.size()];
        for(int i=0; i<habitList.size(); i++){
            result[i] = habitList.get(i).getHabitName();
        }
        return result;
    }

    public static boolean deleteHabit(Habit habit){
        if(!test) {
            //Cancel the notification of the habit that is deleted
            Notifier.cancelAlarm(habit.getId());
        }
        return habitStorage.deleteHabit(habit);
    }

    public static int getHabitListSize(){
        return habitStorage.getHabitList().size();
    }

    public static boolean makeHabitListEmpty(){
        return habitStorage.makeHabitListEmpty();
    }

    public static void deleteHabitByIndex(int index){
        if(!test){
        //Cancel the notification of the habit that is deleted
        Habit habit = getHabitByIndex(index);
        Notifier.cancelAlarm(habit.getId());
        }
        habitStorage.deleteHabitByIndex(index);
    }

    public static Habit getHabitByIndex(int index) {
        return getHabitList().get(index);
    }

    public static int getID(){
        int id = 0;
        if(habitStorage.getHabitList().size() == 0){
            id = 1;
        }
        else{
            ArrayList<Habit> list = habitStorage.getHabitList();
            Habit habit = list.get(list.size()-1);
            id = habit.getId()+1;
        }
        return id;
    }

    public static int getTotalCheckins(){
        int totalCheckIns = 0;
        ArrayList<Habit> list = habitStorage.getHabitList();
        for(int i=0 ; i<list.size() ; i++) {
            totalCheckIns += list.get(i).getDaysCheckedIn();
        }
        return totalCheckIns;
    }

    public static int getTotalDaysPassed(){
        DateManager dateParser = new DateManager();
        int totalDaysPassed = 0;
        ArrayList<Habit> list = habitStorage.getHabitList();
        for(int i=0 ; i<list.size() ; i++) {
            try {
                int days = dateParser.getDaysPassed(list.get(i).getStartDate(), dateParser.getTodaysDate());
                totalDaysPassed += Math.min(days, 66);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return totalDaysPassed;
    }

    public static int getTotalNumGoodHabits(){
        int count = 0;
        ArrayList<Habit> list = habitStorage.getHabitList();
        for(int i=0 ; i<list.size() ; i++) {
            if (list.get(i).getHabitType())
                count += 1;
        }
        return count;
    }

    public static Habit getHabitById(int id)
    {
        Habit habit = null;
        for (int i=0; i<habitStorage.getHabitListSize(); i++)
        {
            if(habitStorage.getHabitList().get(i).getId() == id)
            {
                habit = habitStorage.getHabitList().get(i);
            }
        }
        return habit;
    }
}
