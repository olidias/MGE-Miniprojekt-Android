package ch.hsr.mge.gadgeothek.presentation.reservations;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import ch.hsr.mge.gadgeothek.domain.Reservation;
import ch.hsr.mge.gadgeothek.presentation.gadget.Communicator;

class ReservationsViewHolder extends RecyclerView.ViewHolder{

    private final Communicator<Reservation> reservationCommunicator;

    public ReservationsViewHolder(View itemView, Communicator<Reservation> reservationCommunicator) {
        super(itemView);
        this.reservationCommunicator = reservationCommunicator;
    }
}
