package com.martin.kamenov.finda.textEditor;

import com.martin.kamenov.finda.base.BaseContracts;
import com.martin.kamenov.finda.http.HttpRequester;
import com.martin.kamenov.finda.models.SettingsConfiguration;
import com.martin.kamenov.finda.repositories.GenericCacheRepository;

import java.util.List;

/**
 * Created by Martin on 4.10.2017 г..
 */

public class TextEditorPresenter implements TextEditorContracts.ITextEditorPresenter {
    public final String Url = "https://fierce-crag-61509.herokuapp.com/translate";
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

    private SettingsConfiguration getSettingsConfiguration() {
        GenericCacheRepository<SettingsConfiguration, Long> repo =
                this.mView.getApp().getSettingsConfigurationRepository();
        List<SettingsConfiguration> list = repo.getAll();

        if(list.size()==0) {
            repo.add(new SettingsConfiguration("en","bg"));
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

        this.httpRequester.post(Url, json);
    }

    public void setOriginalText(String text) {
        this.originalText = text;
    }

    public String getOriginalText() {
        return this.originalText;
    }

    private void setTranslateSettings() {
        SettingsConfiguration config = this.getSettingsConfiguration();
        this.from = config.getTranslateFrom();
        this.to = config.getTranslateTo();
    }

}
