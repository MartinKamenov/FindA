package com.martin.kamenov.finda.textEditor;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.martin.kamenov.finda.R;
import com.martin.kamenov.finda.base.BaseContracts;
import com.martin.kamenov.finda.http.HttpRequester;
import com.martin.kamenov.finda.http.contracts.PostHandler;
import com.martin.kamenov.finda.models.SettingsConfiguration;
import com.martin.kamenov.finda.repositories.GenericCacheRepository;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Martin on 4.10.2017 г..
 */

public class TextEditorPresenter implements TextEditorContracts.ITextEditorPresenter, PostHandler {
    public final String Url = "https://mk-translator.herokuapp.com/translate";
    private String originalText;
    private final HttpRequester httpRequester;
    public TextEditorContracts.ITextEditorView mView;
    private TextEditorActivity activity;
    private String from = "en";
    private String to = "bg";

    public TextEditorPresenter(TextEditorActivity activity) {
        this.activity = activity;
        this.httpRequester = new HttpRequester(this.activity);
    }

    public SettingsConfiguration getSettingsConfiguration() {
        GenericCacheRepository<SettingsConfiguration, Long> repo =
                this.mView.getApp().getSettingsConfigurationRepository();
        List<SettingsConfiguration> list = repo.getAll();

        if(list.size()==0) {
            repo.add(new SettingsConfiguration("en","bg", true));
            list = repo.getAll();
        }

        return list.get(0);
    }

    @Override
    public void subscribe(BaseContracts.View view) {
        this.mView = (TextEditorContracts.ITextEditorView) view;
    }

    @Override
    public void unsubscribe() {
        this.mView = null;
    }

    public void translateText(String text) {
        setTranslateSettings();
        setOriginalText(text);
        String json = String.format("{\"txt\":\"%s\",\"from\":\"%s\",\"to\":\"%s\"}", text.replace("\n", "\\n"), from, to);

        this.httpRequester.post(this, Url, json);
    }

    public void setOriginalText(String text) {
        this.originalText = text;
    }

    public String getOriginalText() {
        return this.originalText;
    }

    @Override
    public void handlePost(Call call, final Response response) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    EditText area = (EditText)activity.findViewById(R.id.text_holder);
                    area.setText(response.body().string());
                    Button button = (Button)activity.findViewById(R.id.translate_btn);
                    button.setText("Show original");
                    Toast.makeText(activity, "Text is translated", Toast.LENGTH_SHORT).show();
                    activity.findViewById(R.id.spinner_container).setVisibility(View.GONE);
                    activity.findViewById(R.id.text_editor_container).setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    Toast.makeText(activity, "something went wrong!", Toast.LENGTH_SHORT).show();
                    activity.findViewById(R.id.spinner_container).setVisibility(View.GONE);
                    activity.findViewById(R.id.text_editor_container).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void handleError(Call call, final Exception ex) {
        activity.runOnUiThread(
            new Runnable() {
               @Override
               public void run() {
                   Toast.makeText(activity, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
               }
           }
        );
    }

    private void setTranslateSettings() {
        SettingsConfiguration config = this.getSettingsConfiguration();
        this.from = config.getTranslateFrom();
        this.to = config.getTranslateTo();
    }

}
