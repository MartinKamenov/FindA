package com.example.martin.finda.settings;

import com.example.martin.finda.base.BaseContracts;
import com.example.martin.finda.models.SettingsConfiguration;
import com.example.martin.finda.repositories.GenericCacheRepository;

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

    public String[] getTranslationLanguages() {
        String[] languages = new String[] {
                "en",
                "bg",
                "es"
        };

        return languages;
    }
}