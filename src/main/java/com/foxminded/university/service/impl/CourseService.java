package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends AbstractService<Course> {

    @Autowired
    public CourseService(AbstractDao<Course> dao) {
        super(dao);
    }
}
