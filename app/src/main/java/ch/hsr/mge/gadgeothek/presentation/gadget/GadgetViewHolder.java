package ch.hsr.mge.gadgeothek.presentation.gadget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ch.hsr.mge.gadgeothek.R;

class GadgetViewHolder extends RecyclerView.ViewHolder{
    TextView gadgetTitle;
    TextView gadgetProducer;
    TextView gadgetPrice;

    GadgetViewHolder(View itemView) {
        super(itemView);
        this.gadgetTitle = itemView.findViewById(R.id.gadget_title);
        this.gadgetProducer = itemView.findViewById(R.id.gadget_producer);
        this.gadgetPrice= itemView.findViewById(R.id.gadget_price);

        itemView.setTag(this);
    }
}
