package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.dao.UserDao;
import com.foxminded.university.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> getById(String login) {
        return userDao.getById(login);
    }

    public boolean delete(User user) {
        return userDao.delete(user);
    }

    public boolean update(User user) {
        return userDao.update(user);
    }

    public boolean create(User user) {
        return userDao.create(user);
    }

    public boolean isExist(User user) {
        return userDao.isExist(user);
    }

    public boolean signIn(String login, String password) {
        return userDao.getById(login)
                .map(User::getPassword)
                .filter(pass -> pass.equals(password))
                .isPresent();
    }

    public User signUp(User user) {
        if (userDao.getById(user.getLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        userDao.create(user);
        return user;
    }
}
