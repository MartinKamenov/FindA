package com.example.martin.finda.text_editor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.martin.finda.R;
import com.example.martin.finda.text_editor.EditTextFragment;

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
