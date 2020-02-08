package com.foxminded.university.dao;

import com.foxminded.university.domain.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User> {

    Optional<User> getByEmail(String email);

}
