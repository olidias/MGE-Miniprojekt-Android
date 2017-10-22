package ch.hsr.mge.gadgeothek.domain;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.authentication.LogoutListener;

public class SettingsFragment extends PreferenceFragment {
    private LogoutListener activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
        handleLogoutPreference();
    }

    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
        if(activity instanceof LogoutListener){
            this.activity = (LogoutListener)activity;
        }
        else{
            throw new AssertionError("Activity must implement LogoutListener");
        }
    }


    private void handleLogoutPreference() {
        Preference pref = findPreference("logout_key");
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                activity.OnLogoutClick();
                return false;
            }
        });
    }

}
