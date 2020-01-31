package com.foxminded.university.dao;

import java.util.Optional;

public interface CrudDao<T, E> {

    Optional<T> getById(E id);

    boolean deleteById(T t);

    boolean update(T t);

    boolean create(T t);

    boolean isExist(T t);
}
