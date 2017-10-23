package ch.hsr.mge.gadgeothek;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.hsr.mge.gadgeothek.authentication.LoginActivity;
import ch.hsr.mge.gadgeothek.authentication.LogoutListener;
import ch.hsr.mge.gadgeothek.domain.SettingsFragment;
import ch.hsr.mge.gadgeothek.domain.gadget.GadgetOverviewFragment;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class MainActivity extends Activity implements View.OnClickListener, LogoutListener {
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setNavigationListener();
    }

    private void setNavigationListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.settings:
                        getFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).commit();
                        break;
                    case R.id.overview:
                        getFragmentManager().beginTransaction().replace(R.id.container, new GadgetOverviewFragment()).commit();
                        break;
                    default:
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void OnLogoutClick() {
        //Write changes to sharedpref?
        LibraryService.logout(new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                if(input){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
                else{

                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext()
                               ,R.string.logout_failure,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
