package com.example.martin.finda.textEditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.martin.finda.R;

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

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.text_container, mTextEditorFragment)
                .commit();
    }

    /*public void makeHttp() {
        Request request = new Request.Builder().url("https://fierce-crag-61509.herokuapp.com/users")
                .build();
        client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Toast.makeText(TextEditorActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                            //findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                        } catch (IOException e) {
                            Toast.makeText(TextEditorActivity.this, "something went wrong!", Toast.LENGTH_SHORT).show();
                            //findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }*/
}
