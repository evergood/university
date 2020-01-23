package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends AbstractService<Student> {

    @Autowired
    public StudentService(AbstractDao<Student> dao) {
        super(dao);
    }
}
