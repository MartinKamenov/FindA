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

    public SettingsConfiguration() {

    }

    public SettingsConfiguration(String translateFrom, String translateTo) {
        setTranslateFrom(translateFrom);
        setTranslateTo(translateTo);
    }

    @Generated(hash = 948150374)
    public SettingsConfiguration(Long id, String translateTo,
            String translateFrom) {
        this.id = id;
        this.translateTo = translateTo;
        this.translateFrom = translateFrom;
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
}
