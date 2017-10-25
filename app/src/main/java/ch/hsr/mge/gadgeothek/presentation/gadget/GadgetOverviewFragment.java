package ch.hsr.mge.gadgeothek.presentation.gadget;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class GadgetOverviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private GadgetAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Gadget> gadgetList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.gadget_overview_fragment,container,false);

        recyclerView = root.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        adapter = new GadgetAdapter(gadgetList, gadgetCommunicator);
        recyclerView.setAdapter(adapter);

        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                gadgetList.clear();
                gadgetList.addAll(input);
                if(input.size()>0){
                    getActivity().findViewById(R.id.no_gadgets_message).setVisibility(View.INVISIBLE);
                }else{
                    getActivity().findViewById(R.id.no_gadgets_message).setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                Log.e("GadgetError", message);
            }
        });

        return root;
    }

    private GadgetCommunication gadgetCommunicator = new GadgetCommunication() {
        @Override
        public void transmit(Gadget selectedGadget) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("GADGET", selectedGadget);
            DetailGadgetFragment frag = new DetailGadgetFragment();
            frag.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.container,frag).commit();
        }
    };
}
