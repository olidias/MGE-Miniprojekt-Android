package ch.hsr.mge.gadgeothek.domain.gadget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ch.hsr.mge.gadgeothek.R;

class GadgetAdapter extends RecyclerView.Adapter<GadgetViewHolder>{

    private ArrayList<Gadget> gadgetList;

    GadgetAdapter(ArrayList<Gadget> gadgetList){
        this.gadgetList = gadgetList;
    }


    @Override
    public GadgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.rowlayout, parent, false);
        TextView gadgetTitle = v.findViewById(R.id.gadget_title);
        TextView gadgetProducer = v.findViewById(R.id.gadget_producer);
        TextView gadgetAvailability = v.findViewById(R.id.gadget_availability);
        TextView gadgetPrice= v.findViewById(R.id.gadget_price);

        return new GadgetViewHolder(v,gadgetTitle, gadgetProducer,gadgetAvailability,gadgetPrice);
    }

    @Override
    public void onBindViewHolder(GadgetViewHolder holder, int position) {
        final Gadget gadget = gadgetList.get(position);
        holder.gadgetTitle.setText(gadget.getName());
        holder.gadgetProducer.setText(" by "+gadget.getManufacturer());
        // Check for availability (is reserved? is on loan?)
        // holder.gadgetAvailability.setText(gadget.get);
        holder.gadgetPrice.setText(Double.toString(gadget.getPrice())+".-");
    }

    @Override
    public int getItemCount() {
        return gadgetList.size();
    }
}
