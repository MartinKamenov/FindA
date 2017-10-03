package com.example.martin.finda.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.martin.finda.R;

public class MenuActivity extends AppCompatActivity {

    private MenuFragment mMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mMenuFragment = new MenuFragment();

        mMenuFragment.setPresenter(new MenuPresenter());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mMenuFragment)
                .commit();
    }
}
