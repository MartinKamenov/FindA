package com.example.martin.finda.textEditor;

import android.app.Activity;

import com.example.martin.finda.base.BaseContracts;

/**
 * Created by Martin on 4.10.2017 г..
 */

public class TextEditorContracts {
    public interface ITextEditorPresenter extends BaseContracts.Presenter {
        void translateText(String text);
    }

    public interface ITextEditorView extends BaseContracts.View {
        void searchInGoogle(String text);

        void copyTextToClipboard(String text);

        void makeToast(String message);

        void translateText(String text);

        void setListeners();
    }
}
