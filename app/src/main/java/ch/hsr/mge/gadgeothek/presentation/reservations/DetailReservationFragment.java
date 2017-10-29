package ch.hsr.mge.gadgeothek.presentation.reservations;

import android.app.Fragment;
import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Reservation;

public class DetailReservationFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.detail_reservation_fragment, container, false);
        Reservation reservation = (Reservation) getArguments().getSerializable("RESERVATION");

        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.setTitle("Reservation: "+ reservation.getGadget().getName());


        TextView producer = root.findViewById(R.id.producer);
        producer.setText(reservation.getGadget().getManufacturer());

        TextView condition = root.findViewById(R.id.condition);
        condition.setText(reservation.getGadget().getCondition().name());

        //TODO Availability

        TextView price = root.findViewById(R.id.price);
        price.setText(Double.toString(reservation.getGadget().getPrice())+".-");

        TextView date = root.findViewById(R.id.reservation_date);
        date.setText(reservation.getReservationDate().toString());

        TextView waitingPos = root.findViewById(R.id.waiting_position);
        waitingPos.setText(Integer.toString(reservation.getWaitingPosition()));

        View.OnClickListener listener = (View.OnClickListener)getArguments().getSerializable("CLICKLISTENER");
        root.findViewById(R.id.loan_btn).setOnClickListener(listener);
        root.findViewById(R.id.delete_reservation_btn).setOnClickListener(listener);

        return root;
    }
}
