package com.martin.kamenov.finda.repositories;

/**
 * Created by Martin on 9.10.2017 г..
 */


import org.greenrobot.greendao.AbstractDao;

import java.util.List;

/**
 * Generic repository for CRUD operations with local(cached) data
 *
 * @param <T> the type of the model
 */
public class GenericCacheRepository<T, K> {

    private final AbstractDao<T, K> mDao;

    /**
     * Creates a {@link GenericCacheRepository} instance
     *
     * @param dao a GreenDao instance to work with SQLite
     */
    public GenericCacheRepository(AbstractDao<T, K> dao) {
        mDao = dao;
    }

    public List<T> getAll() {
        List<T> itemsList = mDao.loadAll();
        return itemsList;
    }

    public T add(T obj) {
        mDao.insert(obj);
        return obj;
    }

    public void clearAll() {
        mDao.deleteAll();
    }
}