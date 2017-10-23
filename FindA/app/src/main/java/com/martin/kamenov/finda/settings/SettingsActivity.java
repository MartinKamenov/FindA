package com.martin.kamenov.finda.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.martin.kamenov.finda.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsFragment mSettingsFragment = new SettingsFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, mSettingsFragment)
                .commit();
    }
}
