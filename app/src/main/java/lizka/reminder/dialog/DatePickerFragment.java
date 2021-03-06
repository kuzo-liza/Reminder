package lizka.reminder.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import lizka.reminder.Utils;

import static java.util.Calendar.*;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditText etDate;

    public void setDate (EditText etDate){
        this.etDate = etDate;
    }

    OnDateSetLIstener lIstener;

    public DatePickerFragment(OnDateSetLIstener onDateSetLIstener){
        super();
        lIstener = onDateSetLIstener;
    }

    public interface OnDateSetLIstener{
        void onDateSet(Calendar dateCalendar);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar dateCalendar = getInstance();

        dateCalendar.set(Calendar.YEAR, year);
        dateCalendar.set(Calendar.MONTH, monthOfYear);
        dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        etDate.setText(Utils.getDate(dateCalendar.getTimeInMillis()));

        lIstener.onDateSet(dateCalendar);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        etDate.setText(null);
    }
}
