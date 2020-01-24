package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.service.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseDaoService implements DaoService<Course> {

    AbstractDao<Course> courseDao;

    @Autowired
    public CourseDaoService(AbstractDao<Course> courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Optional<Course> getById(Integer id) {
        return courseDao.getById(id);
    }

    @Override
    public boolean delete(Course course) {
        return courseDao.delete(course);
    }

    @Override
    public boolean update(Course course) {
        return courseDao.update(course);
    }

    @Override
    public boolean create(Course course) {
        return courseDao.create(course);
    }

    @Override
    public boolean isExist(Course course) {
        return courseDao.isExist(course);
    }


}
