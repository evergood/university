package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.User;

import java.util.List;

public interface CourseDao extends CrudDao<Course> {

    List<Course> findCoursesByStudentId(Integer studentId);

    List<User> findStudentsByCourseId(Integer courseId);
}
