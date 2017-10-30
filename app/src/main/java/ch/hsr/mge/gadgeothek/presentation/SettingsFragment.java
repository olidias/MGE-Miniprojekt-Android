package ch.hsr.mge.gadgeothek.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import ch.hsr.mge.gadgeothek.MainActivity;
import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.presentation.authentication.LogoutListener;
import ch.hsr.mge.gadgeothek.service.LibraryService;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.widget.ListPopupWindow.WRAP_CONTENT;

public class SettingsFragment extends PreferenceFragment {
    private LogoutListener activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
        handleLogoutPreference();
        handleServerChangePreference();
    }

    private void handleServerChangePreference() {

        Preference pref = findPreference("server_change_key");
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        LayoutInflater inflater= getLayoutInflater();

        final View layout=inflater.inflate(R.layout.server_settings,null);

        EditText serverAddress = layout.findViewById(R.id.server_address);
        serverAddress.setText(LibraryService.getServerAddress());

        Button okBtn = layout.findViewById(R.id.btn_okay);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt = layout.findViewById(R.id.server_address);
                LibraryService.setServerAddress(txt.getText().toString());
                Toast.makeText(getContext(), "Server set to "+txt.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                alert.setView(layout).show();
                return true;
            }
        });



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
