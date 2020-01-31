package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Student;

import java.util.List;
<<<<<<< HEAD

public interface StudentDao extends CrudDao<Student> {

    List<Course> getStudentCourses();
=======
import java.util.Map;

public interface StudentDao extends CrudDao<Student, Integer> {
    boolean putMark(Integer studentId, Integer courseId, Integer mark);

    Map<String, Character> viewMarks(Integer studentId);

    List<String> viewStudentCourses(Integer course_id);

    boolean insertStudentTimeUnit(Integer studentId, String courseName, Integer timeInitId);

    Map<String, Integer> getStudentSchedule(Integer studentId);
>>>>>>> ServiceLayer
}
