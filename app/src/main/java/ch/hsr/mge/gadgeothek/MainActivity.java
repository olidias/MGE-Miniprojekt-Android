package ch.hsr.mge.gadgeothek;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.hsr.mge.gadgeothek.presentation.authentication.LoginActivity;
import ch.hsr.mge.gadgeothek.presentation.authentication.LogoutListener;
import ch.hsr.mge.gadgeothek.presentation.SettingsFragment;
import ch.hsr.mge.gadgeothek.presentation.gadget.GadgetOverviewFragment;
import ch.hsr.mge.gadgeothek.presentation.loans.LoanOverviewFragment;
import ch.hsr.mge.gadgeothek.presentation.reservations.ReservationsOverviewFragment;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class MainActivity extends Activity implements View.OnClickListener, LogoutListener {
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;

    GadgetOverviewFragment gadgetOverviewFragment = new GadgetOverviewFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    ReservationsOverviewFragment reservationsFragment = new ReservationsOverviewFragment();
    LoanOverviewFragment loansFragment = new LoanOverviewFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ShowFragment(gadgetOverviewFragment);

        setNavigationListener();
    }

    private void ShowFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void setNavigationListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.overview:
                        ShowFragment(gadgetOverviewFragment);
                        break;
                    case R.id.reservations:
                        ShowFragment(reservationsFragment);
                        break;
                    case R.id.loans:
                        ShowFragment(loansFragment);
                        break;
                    case R.id.settings:
                        ShowFragment(settingsFragment);
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
