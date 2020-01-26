package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Lecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LecturerServiceImpl {

    AbstractDao<Lecturer, Integer> lecturerDao;

    @Autowired
    public LecturerServiceImpl(AbstractDao<Lecturer, Integer> lecturerDao) {
        this.lecturerDao = lecturerDao;
    }

    public Optional<Lecturer> getById(Integer id) {
        return lecturerDao.getById(id);
    }

    public boolean delete(Lecturer lecturer) {
        return lecturerDao.delete(lecturer);
    }

    public boolean update(Lecturer lecturer) {
        return lecturerDao.update(lecturer);
    }

    public boolean create(Lecturer lecturer) {
        return lecturerDao.create(lecturer);
    }

    public boolean isExist(Lecturer lecturer) {
        return lecturerDao.isExist(lecturer);
    }
}
