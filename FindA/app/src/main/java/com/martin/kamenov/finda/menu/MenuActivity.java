package com.martin.kamenov.finda.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.martin.kamenov.finda.R;

public class MenuActivity extends AppCompatActivity {

    private MenuFragment mMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mMenuFragment = new MenuFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mMenuFragment)
                .commit();
    }
}
