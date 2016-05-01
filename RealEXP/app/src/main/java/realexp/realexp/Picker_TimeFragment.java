package realexp.realexp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Penquynh on 1/24/2016.
 */
public class Picker_TimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }



    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Toast.makeText(getActivity(), monthsArray[monthOfYear] + " " + String.valueOf(dayOfMonth) +
        //" " + String.valueOf(year), Toast.LENGTH_SHORT).show();
        String s = "AM";
        String m = "" + minute;
        if (minute < 10) m = "0" + minute;

        if (hourOfDay > 12)
        {
            hourOfDay -= 12;
            s = "PM";
        }
        if (hourOfDay == 0) hourOfDay = 12;
        else if (hourOfDay == 12) s = "PM";

        TextView activityText = (TextView)getActivity().findViewById(R.id.time_text);
        activityText.setText(hourOfDay + ":" + m + " " + s);

    }

}
