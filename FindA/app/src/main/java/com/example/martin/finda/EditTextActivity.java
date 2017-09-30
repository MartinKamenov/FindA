package com.example.martin.finda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditTextActivity extends AppCompatActivity {

    private EditTextFragment mEditTextFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        mEditTextFragment = new EditTextFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.text_container, mEditTextFragment)
                .commit();
    }
}
