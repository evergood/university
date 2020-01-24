package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements DaoService<Student> {

    AbstractDao<Student> studentDao;

    @Autowired
    public StudentService(AbstractDao<Student> studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Optional<Student> getById(Integer id) {
        return studentDao.getById(id);
    }

    @Override
    public boolean delete(Student student) {
        return studentDao.delete(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public boolean create(Student student) {
        return studentDao.create(student);
    }

    @Override
    public boolean isExist(Student student) {
        return studentDao.isExist(student);
    }


}
