package realexp.realexp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Picker_DateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    String monthsArray[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sept"
            , "Oct", "Nov", "Dec"};
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
;

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }



    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //Toast.makeText(getActivity(), monthsArray[monthOfYear] + " " + String.valueOf(dayOfMonth) +
        //" " + String.valueOf(year), Toast.LENGTH_SHORT).show();
        TextView activityText = (TextView)getActivity().findViewById(R.id.date_text);
        //activityText.setText(monthsArray[monthOfYear] + " " + String.valueOf(dayOfMonth) +
         //       " " + String.valueOf(year));
        activityText.setText(monthOfYear + "/" + String.valueOf(dayOfMonth) +
               "/" + String.valueOf(year));

    }

}
