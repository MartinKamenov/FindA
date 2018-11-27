package com.martin.kamenov.finda.http;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.martin.kamenov.finda.R;
import com.martin.kamenov.finda.http.contracts.PostHandler;
import com.martin.kamenov.finda.textEditor.TextEditorActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Martin on 6.10.2017 г..
 */

public class HttpRequester {
    private final OkHttpClient client;
    private final TextEditorActivity activity;
    private MediaType JSON;

    public HttpRequester(TextEditorActivity activity) {
        this.activity = activity;
        this.client = new OkHttpClient();
    }

    public void post(final PostHandler handler, String url, String bodyText) {
        JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, bodyText);
        Request request = new Request.Builder()
                .post(body)
                .addHeader("Content-Type","application/json")
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.handleError(call, e);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                handler.handlePost(call, response);
            }
        });
    }
}
