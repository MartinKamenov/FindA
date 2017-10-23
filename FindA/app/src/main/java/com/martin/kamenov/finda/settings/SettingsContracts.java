package com.martin.kamenov.finda.settings;

import com.martin.kamenov.finda.FindAApplication;
import com.martin.kamenov.finda.base.BaseContracts;
import com.martin.kamenov.finda.models.SettingsConfiguration;

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
