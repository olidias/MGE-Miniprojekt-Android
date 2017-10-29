package ch.hsr.mge.gadgeothek.presentation.reservations;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Reservation;
import ch.hsr.mge.gadgeothek.presentation.Communicator;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class ReservationsOverviewFragment extends Fragment implements Serializable, View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReservationsAdapter adapter;

    private ArrayList<Reservation> reservationList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.overview_fragment, container, false);

        CollapsingToolbarLayout toolbar = root.findViewById(R.id.collapsing_toolbar);
        toolbar.setTitle(getResources().getString(R.string.reservations));

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
                Toast.makeText(getActivity().getApplicationContext(),"Error reservation: "+message, Toast.LENGTH_LONG);
            }
        });

        return root;
    }

    private Reservation lastClickedReservation;
    Communicator<Reservation> reservationCommunicator = new Communicator<Reservation>() {

        @Override
        public void transmit(Reservation transmittedItem) {
            lastClickedReservation = transmittedItem;
            Bundle bundle = new Bundle();
            bundle.putSerializable("RESERVATION", transmittedItem);
            bundle.putSerializable("CLICKLISTENER", ReservationsOverviewFragment.this);
            DetailReservationFragment frag = new DetailReservationFragment();
            frag.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.container,frag).addToBackStack("").commit();
        }
    };

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loan_btn:

                break;
            case R.id.delete_reservation_btn:
                LibraryService.deleteReservation(this.lastClickedReservation, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        reservationList.remove(lastClickedReservation);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity().getApplicationContext(),getResources().getString(R.string.reservation_deletion_success), Toast.LENGTH_LONG);
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
