package ch.hsr.mge.gadgeothek.login;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.hsr.mge.gadgeothek.R;

public class RegisterFragment extends Fragment implements View.OnClickListener {


    private View.OnClickListener activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.register_fragment,container,false);
        root.findViewById(R.id.register_button).setOnClickListener(this);
        return root;
    }

    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
        if(activity instanceof View.OnClickListener){
            this.activity = (View.OnClickListener)activity;
        }
        else{
            throw new AssertionError("Activity must implement View.OnClickListener");
        }
    }

    @Override
    public void onClick(View view) {
        TextView pw1 = view.findViewById(R.id.password);
        TextView pw2 = view.findViewById(R.id.password_repeat);

        if(pw1.getText()!=pw2.getText()){
            // Colorize input, show notification
        }
        activity.onClick(view);
    }
}
