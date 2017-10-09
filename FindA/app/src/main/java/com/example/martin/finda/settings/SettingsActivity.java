package com.example.martin.finda.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.martin.finda.FindAApplication;
import com.example.martin.finda.R;
import com.example.martin.finda.models.DaoMaster;
import com.example.martin.finda.models.DaoSession;
import com.example.martin.finda.models.SettingsConfiguration;
import com.example.martin.finda.models.SettingsConfigurationDao;
import com.example.martin.finda.repositories.GenericCacheRepository;

import org.greenrobot.greendao.database.Database;

import java.util.List;

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
