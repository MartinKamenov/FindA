package com.example.martin.finda.textEditor;

import android.app.Activity;

import com.example.martin.finda.base.BaseContracts;
import com.example.martin.finda.http.HttpRequester;

/**
 * Created by Martin on 4.10.2017 г..
 */

public class TextEditorPresenter implements TextEditorContracts.ITextEditorPresenter {
    private final HttpRequester httpRequester;
    public TextEditorContracts.ITextEditorView view;
    private Activity activity;

    public TextEditorPresenter(Activity activity) {
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
        this.httpRequester.get("https://fierce-crag-61509.herokuapp.com/users");
    }

}
