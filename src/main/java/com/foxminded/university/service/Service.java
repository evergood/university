package com.foxminded.university.service;

import java.util.Optional;

public interface Service<T> {
    Optional<T> getById(Integer id);

    boolean delete(T t);

    boolean update(T t);

    boolean create(T t);

    boolean isExist(T t);
}
