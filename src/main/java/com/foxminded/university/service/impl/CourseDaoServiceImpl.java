package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseDaoServiceImpl {

    AbstractDao<Course, Integer> courseDao;

    @Autowired
    public CourseDaoServiceImpl(AbstractDao<Course, Integer> courseDao) {
        this.courseDao = courseDao;
    }

    public Optional<Course> getById(Integer id) {
        return courseDao.getById(id);
    }

    public boolean delete(Course course) {
        return courseDao.delete(course);
    }

    public boolean update(Course course) {
        return courseDao.update(course);
    }

    public boolean create(Course course) {
        return courseDao.create(course);
    }

    public boolean isExist(Course course) {
        return courseDao.isExist(course);
    }


}
