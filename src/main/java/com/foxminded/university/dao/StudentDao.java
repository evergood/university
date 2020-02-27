package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentDao extends CrudDao<Student> {

    Map<String, Character> viewMarks(Integer studentId);

    boolean insertStudentTimeUnit(Integer studentId, String courseName, Integer timeInitId);

    Map<String, Integer> getStudentSchedule(Integer studentId);
    boolean isExistByEmail(String email);

    Optional<Student> getByEmail(String email);

}
