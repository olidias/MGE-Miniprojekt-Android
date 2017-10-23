package ch.hsr.mge.gadgeothek.presentation.gadgetoverview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class GadgetViewHolder extends RecyclerView.ViewHolder {
    TextView gadgetTitle;
    TextView gadgetProducer;
    TextView gadgetAvailability;
    TextView gadgetPrice;

    GadgetViewHolder(View itemView, TextView gadgetTitle, TextView gadgetProducer, TextView gadgetAvailability, TextView gadgetPrice) {
        super(itemView);
        this.gadgetTitle = gadgetTitle;
        this.gadgetProducer = gadgetProducer;
        this.gadgetAvailability = gadgetAvailability;
        this.gadgetPrice = gadgetPrice;
    }
}
