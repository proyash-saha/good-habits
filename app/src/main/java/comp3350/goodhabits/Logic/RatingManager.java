package comp3350.goodhabits.Logic;

public class RatingManager {


    public RatingManager(){

    }

    public float getRating(){
        float totalCheckIns = (float)HabitManager.getTotalCheckins();
        float totalDaysPassed = (float)HabitManager.getTotalDaysPassed();
        return(totalCheckIns / totalDaysPassed) * 5;
    }
}
