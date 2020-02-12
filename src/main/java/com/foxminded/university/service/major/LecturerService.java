package com.foxminded.university.service.major;

import com.foxminded.university.dao.LecturerDao;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.domain.Role;
import com.foxminded.university.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LecturerService {

    private final LecturerDao lecturerDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerService.class);

    @Autowired
    public LecturerService(LecturerDao lecturerDao) {
        this.lecturerDao = lecturerDao;
    }

    public Optional<Lecturer> getById(Integer id) {
        return lecturerDao.getById(id);
    }

    public boolean deleteById(Integer id) {
        return lecturerDao.deleteById(id);
    }

    public boolean update(Lecturer lecturer) {
        return lecturerDao.update(lecturer);
    }

    public boolean create(Lecturer lecturer) {
        return lecturerDao.create(lecturer);
    }

    public boolean isExist(Integer id) {
        return lecturerDao.isExist(id);
    }

    public boolean putMark(User currentUser, Integer studentId, Integer courseId, Character mark) {
        if (currentUser.getRole() != Role.LECTURER) {
            LOGGER.error("User is not  eligible to put marks");
            throw new RuntimeException("You're not eligible to put marks");
        }
        return lecturerDao.putMark(studentId, courseId, mark);
    }
}
