package ch.hsr.mge.gadgeothek;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ch.hsr.mge.gadgeothek.login.LoginFragment;
import ch.hsr.mge.gadgeothek.login.RegisterFragment;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new LoginFragment();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_button:

                break;
            case R.id.register_button:
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment registerFragment = new RegisterFragment();
                fragmentTransaction.replace(R.id.container,registerFragment);
                fragmentTransaction.addToBackStack("register");
                fragmentTransaction.commit();
                break;
            default:
                break;
        };

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fragmentManager.popBackStack();

    }
}
