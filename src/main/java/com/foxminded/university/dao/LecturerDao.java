package com.foxminded.university.dao;

import com.foxminded.university.domain.Lecturer;

import java.util.Optional;

public interface LecturerDao extends CrudDao<Lecturer> {

    boolean putMark(Integer userId, Integer courseId, Character mark);

    boolean isExistByEmail(String email);

    Optional<Lecturer> getByEmail(String email);
}
