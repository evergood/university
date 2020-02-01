package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Student;

import java.util.List;

public interface StudentDao extends CrudDao<Student> {

    List<Course> getStudentCourses();
}
