package com.foxminded.university.dao;

import java.util.Optional;

public interface CrudDao<T> {

    Optional<T> getById(Integer id);

    boolean deleteById(Integer id);

    boolean update(T t);

    boolean create(T t);

    boolean isExist(Integer id);
}
