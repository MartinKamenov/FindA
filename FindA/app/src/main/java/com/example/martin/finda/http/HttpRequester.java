package com.example.martin.finda.http;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

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
    private final Activity activity;
    private MediaType JSON;

    public HttpRequester(Activity activity) {
        this.activity = activity;
        this.client = new OkHttpClient();
    }

    public void post(String url, String bodyText) {
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

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                handlePost(response);
            }
        });
    }

    public void handlePost(final Response response) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Toast.makeText(activity, response.body().string(), Toast.LENGTH_SHORT).show();
                    //findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                } catch (IOException e) {
                    Toast.makeText(activity, "something went wrong!", Toast.LENGTH_SHORT).show();
                    //findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                }
            }
        });
    }
}
