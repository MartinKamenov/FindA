package com.martin.kamenov.finda.settings;

import com.martin.kamenov.finda.base.BaseContracts;
import com.martin.kamenov.finda.models.SettingsConfiguration;
import com.martin.kamenov.finda.repositories.GenericCacheRepository;

import java.util.List;

/**
 * Created by Martin on 9.10.2017 г..
 */

public class SettingsPresenter implements SettingsContracts.ISettingsPresenter {
    private SettingsContracts.ISettingsView mView;

    @Override
    public void subscribe(BaseContracts.View view) {
        this.mView = (SettingsContracts.ISettingsView)view;
    }

    @Override
    public void unsubscribe() {
        this.mView = null;
    }

    public SettingsConfiguration getSettingsConfiguration() {
        GenericCacheRepository<SettingsConfiguration, Long> repo =
                this.mView.getApp().getSettingsConfigurationRepository();
        List<SettingsConfiguration> list = repo.getAll();

        if(list.size()==0) {
            repo.add(new SettingsConfiguration("en","bg"));
            list = repo.getAll();
        }

        return list.get(0);
    }

    public void setSettingsConfiguration(SettingsConfiguration configuration) {
        GenericCacheRepository<SettingsConfiguration, Long> repo =
                this.mView.getApp().getSettingsConfigurationRepository();
        repo.clearAll();
        repo.add(configuration);
    }

    public String[] getTranslationLanguages() {
        String[] languages = new String[] {
                "en",
                "fr",
                "de",
                "it",
                "es",
                "bg",
                "pt",
                "ru"
        };

        return languages;
    }

    public String[] getFullTranslationLanguages() {
        String[] languages = new String[] {
                "English",
                "French",
                "German",
                "Italian",
                "Spanish",
                "Bulgarian",
                "Portuguese",
                "Russian"
        };

        return languages;
    }
}
