package ch.hsr.mge.gadgeothek.presentation.authentication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.MainActivity;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment loginFragment;
    private Fragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.container, loginFragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.login_button:
                loginClick();
                break;
            case R.id.register_button:
                registerClick();
                break;
            case R.id.new_user_button:
                newUserClick();
                break;
            default:
                break;
        }

    }

    private void newUserClick() {
        final EditText username = registerFragment.getView().findViewById(R.id.register_username);
        final EditText pass = registerFragment.getView().findViewById(R.id.register_password);
        final EditText studNum = registerFragment.getView().findViewById(R.id.student_number);
        final EditText mail = registerFragment.getView().findViewById(R.id.email);

        LibraryService.register(mail.getText().toString(), pass.getText().toString(), username.getText().toString(),studNum.getText().toString(), new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                LibraryService.login(username.getText().toString(), pass.getText().toString(), new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        handleLoginCompletion(input);
                    }

                    @Override
                    public void onError(String message) {
                        handleError(getResources().getText(R.string.login_failure).toString(),message);
                    }
                });
            }

            @Override
            public void onError(String message) {
                handleError(getResources().getText(R.string.register_failure).toString(), message);
            }
        });
    }

    private void registerClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        registerFragment = new RegisterFragment();
        fragmentTransaction.replace(R.id.container, registerFragment);
        fragmentTransaction.addToBackStack("register");
        fragmentTransaction.commit();
    }

    private void loginClick() {
        EditText username = loginFragment.getView().findViewById(R.id.username);
        EditText password = loginFragment.getView().findViewById(R.id.password);

        LibraryService.login(username.getText().toString(), password.getText().toString(), new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                handleLoginCompletion(input);
            }
            @Override
            public void onError(String message) {
                handleError(getResources().getText(R.string.login_failure).toString(), message);
            }
        });
    }

    private void handleError(String errorType, String message) {
        Log.e(errorType, message);
        Toast.makeText(this, errorType+": "+message, Toast.LENGTH_SHORT).show();
    }


    private void handleLoginCompletion(Boolean success) {
        if(success){
            startActivity(new Intent(this, MainActivity.class));
        }
        else{
            Log.d("LOGINERROR","Login error!");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fragmentManager.popBackStack();
    }

}
