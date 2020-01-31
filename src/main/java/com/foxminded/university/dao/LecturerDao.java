package com.foxminded.university.dao;

import com.foxminded.university.domain.Lecturer;

public interface LecturerDao extends CrudDao<Lecturer> {

    boolean putMark(Integer userId, Integer courseId, Character mark);
}
