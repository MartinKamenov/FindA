package com.example.martin.finda.textEditor;

import com.example.martin.finda.base.BaseContracts;

/**
 * Created by Martin on 4.10.2017 г..
 */

public class TextEditorPresenter implements TextEditorContracts.ITextEditorPresenter {
    public TextEditorContracts.ITextEditorView view;
    @Override
    public void subscribe(BaseContracts.View view) {
        this.view = (TextEditorContracts.ITextEditorView) view;
    }

    @Override
    public void unsubscribe() {
        this.view = null;
    }
}
