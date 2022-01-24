package comp3350.goodhabits.Logic;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateManager {

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public DateManager(){

    }

    public String getTodaysDate(){
        Date startDate = new Date();
        return dateFormat.format(startDate);
    }

    public String getEndDate(String startDate) throws ParseException{
        Date date = dateFormat.parse(startDate);
        Calendar calendar = Calendar.getInstance();
        assert date != null;
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 66);
        Date endDate = calendar.getTime();
        return dateFormat.format(endDate);
    }

    public boolean habitLastDay(String startDate, String endDate){
        return startDate.equals(endDate);
    }

    public int getDaysPassed(String startDate, String currDate) throws ParseException {
        Date date1 = dateFormat.parse(startDate);
        Date date2 = dateFormat.parse(currDate);

        assert date2 != null;
        assert date1 != null;
        long difference = date2.getTime() - date1.getTime();
        float days = (float)(difference / (1000 *60*60*24));
        return (int)days;
    }
}
