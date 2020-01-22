package com.foxminded.university.service;

import java.util.Map;

public interface UniversityService {
    boolean checkLogin(String login);

    boolean signUp(String login, String password);

    boolean putMark(Integer studentId, Integer courseId, Integer mark);

    Map<String, Integer> viewMarks(Integer studentId);
}
