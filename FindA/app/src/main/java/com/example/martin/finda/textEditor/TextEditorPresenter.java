package com.example.martin.finda.textEditor;

import android.app.Activity;
import android.widget.Toast;

import com.example.martin.finda.base.BaseContracts;
import com.example.martin.finda.http.HttpRequester;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Martin on 4.10.2017 г..
 */

public class TextEditorPresenter implements TextEditorContracts.ITextEditorPresenter {
    public final String Url = "https://fierce-crag-61509.herokuapp.com/translate";
    private String originalText;
    private final HttpRequester httpRequester;
    public TextEditorContracts.ITextEditorView view;
    private TextEditorActivity activity;
    private String from = "en";
    private String to = "bg";

    public TextEditorPresenter(TextEditorActivity activity) {
        this.activity = activity;
        this.httpRequester = new HttpRequester(this.activity);
    }

    @Override
    public void subscribe(BaseContracts.View view) {
        this.view = (TextEditorContracts.ITextEditorView) view;
    }

    @Override
    public void unsubscribe() {
        this.view = null;
    }

    public void translateText(String text) {
        setOriginalText(text);
        String json = String.format("{\"txt\":\"%s\",\"from\":\"%s\",\"to\":\"%s\"}", text.replace("\n", "\\n"), from, to);

        this.httpRequester.post(Url, json);
    }

    public void setOriginalText(String text) {
        this.originalText = text;
    }

    public String getOriginalText() {
        return this.originalText;
    }

}
