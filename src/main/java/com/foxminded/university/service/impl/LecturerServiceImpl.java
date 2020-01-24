package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.service.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LecturerServiceImpl implements DaoService<Lecturer, Integer> {

    AbstractDao<Lecturer, Integer> lecturerDao;

    @Autowired
    public LecturerServiceImpl(AbstractDao<Lecturer, Integer> lecturerDao) {
        this.lecturerDao = lecturerDao;
    }

    @Override
    public Optional<Lecturer> getById(Integer id) {
        return lecturerDao.getById(id);
    }

    @Override
    public boolean delete(Lecturer lecturer) {
        return lecturerDao.delete(lecturer);
    }

    @Override
    public boolean update(Lecturer lecturer) {
        return lecturerDao.update(lecturer);
    }

    @Override
    public boolean create(Lecturer lecturer) {
        return lecturerDao.create(lecturer);
    }

    @Override
    public boolean isExist(Lecturer lecturer) {
        return lecturerDao.isExist(lecturer);
    }
}
