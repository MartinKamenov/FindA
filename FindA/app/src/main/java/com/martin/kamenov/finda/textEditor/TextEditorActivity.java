package com.martin.kamenov.finda.textEditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.martin.kamenov.finda.R;

import okhttp3.OkHttpClient;

public class TextEditorActivity extends AppCompatActivity {

    private TextEditorFragment mTextEditorFragment;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        mTextEditorFragment = new TextEditorFragment();
        /*mHttpRequester = new HttpRequester();
        mPresenter = new TextEditorPresenter(mHttpRequester);
        mTextEditorFragment.setPresenter(mPresenter);*/

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.text_container, mTextEditorFragment)
                .commit();
    }
}
