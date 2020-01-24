package com.foxminded.university.service;

import com.foxminded.university.domain.User;

import java.util.Map;

public interface UserService extends DaoService<User, String> {
    boolean signIn(String login, String password);

    User signUp(User user);

    boolean putMark(Integer studentId, Integer courseId, Integer mark);

    Map<String, Integer> viewMarks(Integer studentId);
}
