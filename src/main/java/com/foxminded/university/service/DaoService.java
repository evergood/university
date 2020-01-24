package com.foxminded.university.service;

import java.util.Optional;

public interface DaoService<T, E> {
    Optional<T> getById(E id);

    boolean delete(T t);

    boolean update(T t);

    boolean create(T t);

    boolean isExist(T t);
}
