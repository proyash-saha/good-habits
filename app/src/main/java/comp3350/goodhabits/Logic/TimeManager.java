package comp3350.goodhabits.Logic;

public class TimeManager {

    public TimeManager(){

    }

    public String getTime(int hourOfDay, int minuteOfHour){
        String flag = hourOfDay >= 12? "PM" : "AM";
        String tempHour = (hourOfDay%12) == 0? "12" : String.valueOf(hourOfDay%12);
        String tempMin = minuteOfHour <= 9? "0"+ minuteOfHour : String.valueOf(minuteOfHour);
        return tempHour+":"+tempMin+" "+flag;
    }
}
