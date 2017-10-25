package ch.hsr.mge.gadgeothek.presentation.gadget;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Gadget;

public class DetailGadgetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.detail_gadget_fragment, container, false);
        Gadget gadget = (Gadget) getArguments().getSerializable("GADGET");
        Toolbar toolbar =root.findViewById(R.id.toolbar);
        toolbar.setTitle(gadget.getName());
        TextView title = root.findViewById(R.id.description_text);

        title.setText(gadget.getName());
        return root;
    }


}
