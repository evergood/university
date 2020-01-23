package com.foxminded.university.service;

import com.foxminded.university.dao.AbstractDao;

import java.util.Optional;

public class AbstractService<T> implements Service<T>{

    AbstractDao<T> dao;

    public AbstractService(AbstractDao<T> dao) {
        this.dao = dao;
    }


    @Override
    public Optional<T> getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public boolean delete(T t) {
        return dao.delete(t);
    }

    @Override
    public boolean update(T t) {
        return dao.update(t);
    }

    @Override
    public boolean create(T t) {
        return dao.create(t);
    }

    @Override
    public boolean isExist(T t) {
        return dao.isExist(t);
    }
}
