package ch.hsr.mge.gadgeothek.login;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ch.hsr.mge.gadgeothek.R;

public class RegisterFragment extends Fragment implements View.OnClickListener {


    private View.OnClickListener activity;

    @BindView(R.id.password) EditText password;
    @BindView(R.id.password_repeat) EditText password_repeat;
    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.register_fragment,container,false);
        root.findViewById(R.id.new_user_button).setOnClickListener(this);
        unbinder = ButterKnife.bind(this, root);

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
        if(!Objects.equals(password.getText().toString(), password_repeat.getText().toString())){
            password.setTextColor(Color.RED);
            password_repeat.setTextColor(Color.RED);
        }
        activity.onClick(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
