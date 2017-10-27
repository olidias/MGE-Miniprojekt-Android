package ch.hsr.mge.gadgeothek.presentation.gadget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Gadget;

class GadgetAdapter extends RecyclerView.Adapter<GadgetViewHolder> implements View.OnClickListener{

    private final Communicator communicator;
    private ArrayList<Gadget> gadgetList;

    GadgetAdapter(ArrayList<Gadget> gadgetList, Communicator communicator){
        this.gadgetList = gadgetList;
        this.communicator = communicator;
    }

    @Override
    public GadgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        final View v = layoutInflater.inflate(R.layout.gadget_rowlayout, parent, false);

        v.setOnClickListener(this);

        return new GadgetViewHolder(v);
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

    @Override
    public void onClick(View view) {
        GadgetViewHolder holder = (GadgetViewHolder) view.getTag();
        communicator.transmit(gadgetList.get(holder.getAdapterPosition()));
    }
}
