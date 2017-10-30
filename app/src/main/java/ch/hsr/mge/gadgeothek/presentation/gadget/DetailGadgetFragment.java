package ch.hsr.mge.gadgeothek.presentation.gadget;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.presentation.date.DatePickerFragment;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class DetailGadgetFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener, Serializable{

    private Gadget gadget;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.detail_gadget_fragment, container, false);

        this.gadget = (Gadget) getArguments().getSerializable("GADGET");
        Toolbar toolbar =root.findViewById(R.id.toolbar);
        toolbar.setTitle(gadget.getName());

        TextView condition = root.findViewById(R.id.condition);
        condition.setText(gadget.getCondition().name());

        TextView producer = root.findViewById(R.id.producer);
        producer.setText(gadget.getManufacturer());

        TextView price = root.findViewById(R.id.price);
        price.setText(Double.toString(gadget.getPrice())+".-");

        Button reserveButton = root.findViewById(R.id.reserve_btn);
        reserveButton.setOnClickListener(this);

        Button loanButton = root.findViewById(R.id.loan_btn);
        loanButton.setOnClickListener(this);

        return root;
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.reserve_btn:
                LibraryService.reserveGadget(this.gadget,this.reserveCallback);
                break;
            case R.id.loan_btn:
                Bundle bundle = new Bundle();
                bundle.putSerializable("LISTENER", this);
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setArguments(bundle);
                datePicker.show(getFragmentManager(), "DatePicker");
                break;
            default:
                break;
        }
    }

    private Callback<Boolean> reserveCallback = new Callback<Boolean>() {
        @Override
        public void onCompletion(Boolean input) {
            Toast.makeText(getActivity().getApplicationContext()
                    ,R.string.reserve_success,
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getActivity().getApplicationContext()
                    ,getResources().getString(R.string.reserve_failure) + message,
                    Toast.LENGTH_LONG).show();
        }
    };

    private Callback<Boolean> loanCallback = new Callback<Boolean>() {
        @Override
        public void onCompletion(Boolean input) {
            Toast.makeText(getActivity().getApplicationContext()
                    ,R.string.loan_success,
                    Toast.LENGTH_LONG).show();
            getFragmentManager().popBackStack();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getActivity().getApplicationContext()
                    ,getResources().getString(R.string.loan_failure) + message,
                    Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar today = Calendar.getInstance();
        Calendar returnDate = Calendar.getInstance();
        returnDate.set(i,i1,i2);
        String loanId = UUID.randomUUID().toString();
        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {

            }

            @Override
            public void onError(String message) {

            }
        });
        Loan l = new Loan(loanId,this.gadget,today.getTime(), returnDate.getTime());
        LibraryService.loanGadget(l,loanCallback);
    }
}
