package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;

import java.util.Map;

public interface StudentDao extends CrudDao<Student, Integer> {
    boolean putMark(Integer studentId, Integer courseId, Integer mark);

    Map<String, Character> viewMarks(Integer studentId);
}
