package com.example.martin.finda.textEditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.martin.finda.R;
import com.example.martin.finda.http.HttpRequester;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
