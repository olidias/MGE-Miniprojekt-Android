package ch.hsr.mge.gadgeothek.presentation.loans;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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
import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


public class LoanOverviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private LoanAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Loan> loanList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.overview_fragment,container,false);

        CollapsingToolbarLayout toolbar = root.findViewById(R.id.collapsing_toolbar);
        toolbar.setTitle(getResources().getString(R.string.loans));
        toolbar.setCollapsedTitleTextColor(Color.WHITE);
        toolbar.setExpandedTitleColor(Color.WHITE);

        recyclerView = root.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        adapter = new LoanAdapter(loanList);
        recyclerView.setAdapter(adapter);

        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                loanList.clear();
                loanList.addAll(input);
                if(input.size()>0){
                    getActivity().findViewById(R.id.no_gadgets_message).setVisibility(View.INVISIBLE);
                }else{
                    getActivity().findViewById(R.id.no_gadgets_message).setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                Log.e("LoanError", message);
            }
        });

        return root;
    }


}
