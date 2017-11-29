package com.martin.kamenov.finda.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Martin on 8.10.2017 г..
 */

@Entity(nameInDb = "settingsConfiguration")
public class SettingsConfiguration {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "translateTo")
    private String translateTo;

    @Property(nameInDb = "translateFrom")
    private String translateFrom;

    @Property(nameInDb = "voiceRecognition")
    private boolean voiceRecognition;

    public SettingsConfiguration() {

    }

    public SettingsConfiguration(String translateFrom, String translateTo, boolean voiceRecognition) {
        setTranslateFrom(translateFrom);
        setTranslateTo(translateTo);
        setVoiceRecognition(voiceRecognition);
    }

    @Generated(hash = 1863476006)
    public SettingsConfiguration(Long id, String translateTo, String translateFrom,
            boolean voiceRecognition) {
        this.id = id;
        this.translateTo = translateTo;
        this.translateFrom = translateFrom;
        this.voiceRecognition = voiceRecognition;
    }

    public String getTranslateTo() {
        return translateTo;
    }

    public void setTranslateTo(String value) {
        translateTo = value;
    }

    public String getTranslateFrom() {
        return translateFrom;
    }

    public void setTranslateFrom(String value) {
        translateFrom = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getVoiceRecognition() {
        return this.voiceRecognition;
    }

    public void setVoiceRecognition(boolean voiceRecognition) {
        this.voiceRecognition = voiceRecognition;
    }
}
