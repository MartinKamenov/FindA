package com.example.martin.finda.textEditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.martin.finda.R;

public class TextEditorActivity extends AppCompatActivity {

    private TextEditorFragment mTextEditorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        mTextEditorFragment = new TextEditorFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.text_container, mTextEditorFragment)
                .commit();
    }
}
