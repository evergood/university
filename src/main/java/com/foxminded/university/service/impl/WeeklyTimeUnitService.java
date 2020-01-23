package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.WeeklyTimeUnit;
import com.foxminded.university.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeeklyTimeUnitService extends AbstractService<WeeklyTimeUnit> {

    @Autowired
    public WeeklyTimeUnitService(AbstractDao<WeeklyTimeUnit> dao) {
        super(dao);
    }
}
