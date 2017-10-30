package ch.hsr.mge.gadgeothek.presentation.loans;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.presentation.Communicator;

class LoanAdapter extends RecyclerView.Adapter<LoanViewHolder> {

    private ArrayList<Loan> loanList;

    LoanAdapter(ArrayList<Loan> loanList){
        this.loanList = loanList;
    }

    @Override
    public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        final View v = layoutInflater.inflate(R.layout.loan_rowlayout, parent, false);

        return new LoanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LoanViewHolder holder, int position) {
        final Loan loan  = loanList.get(position);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        holder.gadgetTitle.setText(loan.getGadget().getName());
        holder.reservationDate.setText(df.format(loan.getPickupDate()));
        if(loan.getReturnDate()!=null) {
            holder.bringBackDate.setText(df.format(loan.getReturnDate()));
        }
    }

    @Override
    public int getItemCount() {
        return loanList.size();
    }

}
