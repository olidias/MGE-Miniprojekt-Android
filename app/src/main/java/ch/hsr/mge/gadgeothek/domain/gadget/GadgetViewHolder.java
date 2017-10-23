package ch.hsr.mge.gadgeothek.domain.gadget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class GadgetViewHolder extends RecyclerView.ViewHolder {


    public TextView gadgetTitle;
    public TextView gadgetProducer;
    public TextView gadgetAvailability;
    public TextView gadgetPrice;

    public GadgetViewHolder(View itemView, TextView gadgetTitle, TextView gadgetProducer, TextView gadgetAvailability, TextView gadgetPrice) {
        super(itemView);
        this.gadgetTitle = gadgetTitle;
        this.gadgetProducer = gadgetProducer;
        this.gadgetAvailability = gadgetAvailability;
        this.gadgetPrice = gadgetPrice;
    }
}
