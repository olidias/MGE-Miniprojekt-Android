package ch.hsr.mge.gadgeothek.presentation.date;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.presentation.gadget.DetailGadgetFragment;

public class DatePickerFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DetailGadgetFragment listener=null;
        Bundle bundle = getArguments();
        if(bundle!=null) {
            listener = (DetailGadgetFragment) bundle.getSerializable("LISTENER");
        }
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, year, month, day);
        dialog.setTitle(getResources().getString(R.string.select_pickup_date));

        return dialog;
    }
}
