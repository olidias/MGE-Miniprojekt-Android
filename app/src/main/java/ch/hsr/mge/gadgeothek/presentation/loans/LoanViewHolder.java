package ch.hsr.mge.gadgeothek.presentation.loans;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ch.hsr.mge.gadgeothek.R;

class LoanViewHolder extends RecyclerView.ViewHolder{
    TextView gadgetTitle;
    TextView reservationDate;
    TextView bringBackDate;

    LoanViewHolder(View itemView) {
        super(itemView);
        this.gadgetTitle = itemView.findViewById(R.id.gadget_title);
        this.reservationDate = itemView.findViewById(R.id.reservation_date);
        this.bringBackDate = itemView.findViewById(R.id.bring_back_date);
        itemView.setTag(this);
    }
}
