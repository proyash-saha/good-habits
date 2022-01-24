package comp3350.goodhabits.Logic;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

//This class is used to create a Time Picker, which is used to take "Time" inputs
public class TimePickerFragment extends DialogFragment {
    @NonNull
    @Override
    //This Function Prompts the user to select the desired time, default time is set to current time
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();    // Calendar is used to take the current time
        int hour = c.get(Calendar.HOUR_OF_DAY);    // Current Hour
        int minute = c.get(Calendar.MINUTE);    // Current Minute

        // Returns the time selected by the user, shows current time as initial time
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour,minute, DateFormat.is24HourFormat(getActivity()));
    }
}
