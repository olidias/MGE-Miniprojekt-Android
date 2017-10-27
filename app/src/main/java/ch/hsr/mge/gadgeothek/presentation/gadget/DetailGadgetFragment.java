package ch.hsr.mge.gadgeothek.presentation.gadget;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class DetailGadgetFragment extends Fragment implements View.OnClickListener{

    private Gadget gadget;
    private Callback<Boolean> loanCallback = new Callback<Boolean>() {
        @Override
        public void onCompletion(Boolean input) {
            Toast.makeText(getActivity().getApplicationContext()
                    ,R.string.loan_success,
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getActivity().getApplicationContext()
                    ,getResources().getString(R.string.loan_failure) + message,
                    Toast.LENGTH_LONG).show();

        }
    };

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

        TextView availability = root.findViewById(R.id.availability);
        // TODO Check availability

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
                //TODO Datepicker for start/endtime of loan
                // Loan l = new Loan()
                // LibraryService.loanGadget(l,this.loanCallback);
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

}
