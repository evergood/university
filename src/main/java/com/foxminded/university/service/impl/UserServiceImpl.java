package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.User;
import com.foxminded.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    AbstractDao<User, String> userDao;

    @Autowired
    public UserServiceImpl(AbstractDao<User, String> userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> getById(String login) {
        return userDao.getById(login);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean create(User user) {
        return userDao.create(user);
    }

    @Override
    public boolean isExist(User user) {
        return userDao.isExist(user);
    }

    @Override
    public boolean signIn(String login, String password) {
        return userDao.getById(login)
                .map(User::getPassword)
                .filter(pass -> pass.equals(password))
                .isPresent();
    }

    @Override
    public User signUp(User user) {
        if (userDao.getById(user.getLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        userDao.create(user);
        return user;
    }

    @Override
    public boolean putMark(Integer studentId, Integer courseId, Integer mark) {
        return false;
    }

    @Override
    public Map<String, Integer> viewMarks(Integer studentId) {
        return null;
    }
}
