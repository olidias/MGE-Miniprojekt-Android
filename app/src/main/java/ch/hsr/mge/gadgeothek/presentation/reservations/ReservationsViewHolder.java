package ch.hsr.mge.gadgeothek.presentation.reservations;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ch.hsr.mge.gadgeothek.R;

class ReservationsViewHolder extends RecyclerView.ViewHolder{

     TextView gadgetTitle;
     TextView gadgetProducer;
     TextView reservationDate;
     TextView waitingPosition;
     TextView isReady;

    public ReservationsViewHolder(View itemView) {
        super(itemView);
        this.gadgetTitle = itemView.findViewById(R.id.gadget_title);
        this.gadgetProducer = itemView.findViewById(R.id.gadget_producer);
        this.reservationDate= itemView.findViewById(R.id.reservation_date);
        this.waitingPosition = itemView.findViewById(R.id.waiting_position);
        this.isReady = itemView.findViewById(R.id.is_ready);

        itemView.setTag(this);
    }
}
