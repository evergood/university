package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao extends CrudDao<Student> {

    Map<String, Character> viewMarks(Integer studentId);

    boolean insertStudentTimeUnit(Integer studentId, String courseName, Integer timeInitId);

    Map<String, Integer> getStudentSchedule(Integer studentId);

    List<Student> getAllStudents(int page);
}
