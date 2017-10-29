package ch.hsr.mge.gadgeothek.presentation.reservations;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Reservation;
import ch.hsr.mge.gadgeothek.presentation.Communicator;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class ReservationsOverviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReservationsAdapter adapter;

    private ArrayList<Reservation> reservationList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.overview_fragment, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ReservationsAdapter(this.reservationList,this.reservationCommunicator);
        recyclerView.setAdapter(adapter);

        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                reservationList.clear();
                reservationList.addAll(input);

                if(input.size()>0){
                    getActivity().findViewById(R.id.no_gadgets_message).setVisibility(View.INVISIBLE);
                }else{
                    getActivity().findViewById(R.id.no_gadgets_message).setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getActivity().getApplicationContext(),"Error resevation: "+message, Toast.LENGTH_LONG);
            }
        });

        return root;
    }

    Communicator<Reservation> reservationCommunicator = new Communicator<Reservation>() {
        @Override
        public void transmit(Reservation transmittedItem) {

        }
    };
}
