package ch.hsr.mge.gadgeothek;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.hsr.mge.gadgeothek.domain.SettingsFragment;

public class MainActivity extends Activity implements View.OnClickListener{
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


}
