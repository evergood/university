package com.foxminded.university.service.major;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Optional<Course> getById(Integer id) {
        return courseDao.getById(id);
    }

    public boolean deleteById(Integer id) {
        return courseDao.deleteById(id);
    }

    public boolean update(Course course) {
        return courseDao.update(course);
    }

    public boolean create(Course course) {
        return courseDao.create(course);
    }

    public boolean isExist(Integer id) {
        return courseDao.isExist(id);
    }

    public List<Course> findCoursesByStudentId(Integer studentId) {
        return courseDao.findCoursesByStudentId(studentId);
    }

    public List<User> findStudentsByCourseId(Integer courseId) {
        return courseDao.findStudentsByCourseId(courseId);
    }


}
