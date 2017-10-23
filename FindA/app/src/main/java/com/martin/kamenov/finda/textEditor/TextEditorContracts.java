package com.martin.kamenov.finda.textEditor;

import com.martin.kamenov.finda.FindAApplication;
import com.martin.kamenov.finda.base.BaseContracts;

/**
 * Created by Martin on 4.10.2017 г..
 */

public class TextEditorContracts {
    public interface ITextEditorPresenter extends BaseContracts.Presenter {
        void translateText(String text);

        String getOriginalText();

        void setOriginalText(String text);
    }

    public interface ITextEditorView extends BaseContracts.View {
        void searchInGoogle(String text);

        void copyTextToClipboard(String text);

        void makeToast(String message);

        void translateText(String text);

        void setListeners();

        FindAApplication getApp();
    }
}
