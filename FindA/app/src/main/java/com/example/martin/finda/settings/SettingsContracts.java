package com.example.martin.finda.settings;

import com.example.martin.finda.FindAApplication;
import com.example.martin.finda.base.BaseContracts;
import com.example.martin.finda.models.SettingsConfiguration;

/**
 * Created by Martin on 9.10.2017 г..
 */

public class SettingsContracts {
    public interface ISettingsPresenter extends BaseContracts.Presenter {
        SettingsConfiguration getSettingsConfiguration();
        String[] getTranslationLanguages();
        String[] getFullTranslationLanguages();
        void setSettingsConfiguration(SettingsConfiguration configuration);
    }

    public interface ISettingsView extends BaseContracts.View {
        FindAApplication getApp();
    }
}
