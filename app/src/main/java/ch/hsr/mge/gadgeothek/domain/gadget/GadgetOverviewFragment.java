package ch.hsr.mge.gadgeothek.domain.gadget;

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
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class GadgetOverviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private GadgetAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.gadget_overview_fragment,container,false);

        recyclerView = root.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);


        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                adapter = new GadgetAdapter(new ArrayList<>(input));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String message) {
                Log.e("GadgetError", message);

            }
        });

        return root;
    }

}
