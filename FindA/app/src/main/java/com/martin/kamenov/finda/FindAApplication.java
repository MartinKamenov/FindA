package com.martin.kamenov.finda;

import android.app.Application;

import com.martin.kamenov.finda.models.DaoMaster;
import com.martin.kamenov.finda.models.DaoSession;
import com.martin.kamenov.finda.models.SettingsConfiguration;
import com.martin.kamenov.finda.repositories.GenericCacheRepository;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Martin on 9.10.2017 г..
 */

public class FindAApplication extends Application {
    private DaoSession mDaoSession;
    private GenericCacheRepository<SettingsConfiguration, Long> mSettingsConfigurationRepository;

    public FindAApplication() {
        super();
    }

    /*private void init() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "finda-db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }*/
    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "finda-db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public GenericCacheRepository<SettingsConfiguration, Long> getSettingsConfigurationRepository() {
        if (mSettingsConfigurationRepository == null) {
            mSettingsConfigurationRepository = new GenericCacheRepository<>(mDaoSession.getSettingsConfigurationDao());
        }

        return mSettingsConfigurationRepository;
    }
}
