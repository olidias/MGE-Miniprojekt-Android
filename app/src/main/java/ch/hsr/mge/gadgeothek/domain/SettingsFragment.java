package ch.hsr.mge.gadgeothek.domain;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import ch.hsr.mge.gadgeothek.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
        Preference pref = findPreference("logout_key");
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return false;
            }
        });

    }

}
