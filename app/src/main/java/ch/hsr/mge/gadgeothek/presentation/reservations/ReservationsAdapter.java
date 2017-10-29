package ch.hsr.mge.gadgeothek.presentation.reservations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Reservation;
import ch.hsr.mge.gadgeothek.presentation.Communicator;

class ReservationsAdapter extends RecyclerView.Adapter<ReservationsViewHolder> implements View.OnClickListener{

    private final Communicator<Reservation> reservationCommunicator;
    private final ArrayList<Reservation> reservationList;

    public ReservationsAdapter(ArrayList<Reservation> reservationList, Communicator<Reservation> reservationCommunicator){
        this.reservationList = reservationList;
        this.reservationCommunicator = reservationCommunicator;
    }

    @Override
    public ReservationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        final View v = layoutInflater.inflate(R.layout.reservation_rowlayout, parent, false);

        v.setOnClickListener(this);

        return new ReservationsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ReservationsViewHolder holder, int position) {
        final Reservation res = reservationList.get(position);
        holder.gadgetTitle.setText(res.getGadget().getName());
        holder.gadgetProducer.setText(res.getGadget().getManufacturer());
        holder.reservationDate.setText(res.getReservationDate().toString());
        holder.isReady.setText(res.isReady()?"Ready to pick up":  "Not Ready yet");
        holder.waitingPosition.setText(Integer.toString(res.getWaitingPosition()));
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    @Override
    public void onClick(View view) {
        ReservationsViewHolder holder = (ReservationsViewHolder) view.getTag();
        reservationCommunicator.transmit(reservationList.get(holder.getAdapterPosition()));
    }
}
