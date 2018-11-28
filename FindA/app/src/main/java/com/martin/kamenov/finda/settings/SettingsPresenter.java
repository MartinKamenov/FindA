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
            repo.add(new SettingsConfiguration("en","bg", true));
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
                "af",
                "sq",
                "am",
                "ar",
                "hy",
                "az",
                "eu",
                "be",
                "bn",
                "bs",
                "bg",
                "ca",
                "ceb",
                "ny",
                "zh-cn",
                "zh-tw",
                "co",
                "hr",
                "cs",
                "da",
                "nl",
                "en",
                "eo",
                "et",
                "tl",
                "fi",
                "fr",
                "fy",
                "gl",
                "ka",
                "de",
                "el",
                "gu",
                "ht",
                "ha",
                "haw",
                "iw",
                "hi",
                "hmn",
                "hu",
                "is",
                "ig",
                "id",
                "ga",
                "it",
                "ja",
                "jw",
                "kn",
                "kk",
                "km",
                "ko",
                "ku",
                "ky",
                "lo",
                "la",
                "lv",
                "lt",
                "lb",
                "mk",
                "mg",
                "ms",
                "ml",
                "mt",
                "mi",
                "mr",
                "mn",
                "my",
                "ne",
                "no",
                "ps",
                "fa",
                "pl",
                "pt",
                "ma",
                "ro",
                "ru",
                "sm",
                "gd",
                "sr",
                "st",
                "sn",
                "sd",
                "si",
                "sk",
                "sl",
                "so",
                "es",
                "su",
                "sw",
                "sv",
                "tg",
                "ta",
                "te",
                "th",
                "tr",
                "uk",
                "ur",
                "uz",
                "vi",
                "cy",
                "xh",
                "yi",
                "yo",
                "zu"
        };

        return languages;
    }

    public String[] getFullTranslationLanguages() {
        String[] languages = new String[] {
                "Afrikaans",
                "Albanian",
                "Amharic",
                "Arabic",
                "Armenian",
                "Azerbaijani",
                "Basque",
                "Belarusian",
                "Bengali",
                "Bosnian",
                "Bulgarian",
                "Catalan",
                "Cebuano",
                "Chichewa",
                "Chinese Simplified",
                "Chinese Traditional",
                "Corsican",
                "Croatian",
                "Czech",
                "Danish",
                "Dutch",
                "English",
                "Esperanto",
                "Estonian",
                "Filipino",
                "Finnish",
                "French",
                "Frisian",
                "Galician",
                "Georgian",
                "German",
                "Greek",
                "Gujarati",
                "Haitian Creole",
                "Hausa",
                "Hawaiian",
                "Hebrew",
                "Hindi",
                "Hmong",
                "Hungarian",
                "Icelandic",
                "Igbo",
                "Indonesian",
                "Irish",
                "Italian",
                "Japanese",
                "Javanese",
                "Kannada",
                "Kazakh",
                "Khmer",
                "Korean",
                "Kurdish (Kurmanji)",
                "Kyrgyz",
                "Lao",
                "Latin",
                "Latvian",
                "Lithuanian",
                "Luxembourgish",
                "Macedonian",
                "Malagasy",
                "Malay",
                "Malayalam",
                "Maltese",
                "Maori",
                "Marathi",
                "Mongolian",
                "Myanmar (Burmese)",
                "Nepali",
                "Norwegian",
                "Pashto",
                "Persian",
                "Polish",
                "Portuguese",
                "Punjabi",
                "Romanian",
                "Russian",
                "Samoan",
                "Scots Gaelic",
                "Serbian",
                "Sesotho",
                "Shona",
                "Sindhi",
                "Sinhala",
                "Slovak",
                "Slovenian",
                "Somali",
                "Spanish",
                "Sundanese",
                "Swahili",
                "Swedish",
                "Tajik",
                "Tamil",
                "Telugu",
                "Thai",
                "Turkish",
                "Ukrainian",
                "Urdu",
                "Uzbek",
                "Vietnamese",
                "Welsh",
                "Xhosa",
                "Yiddish",
                "Yoruba",
                "Zulu"
        };

        return languages;
    }
}
