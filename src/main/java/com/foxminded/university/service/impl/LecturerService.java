package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerService extends AbstractService<Lecturer> {

    @Autowired
    public LecturerService(AbstractDao<Lecturer> dao) {
        super(dao);
    }
}
