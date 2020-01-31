package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.dao.WeeklyTimeUnitDao;
import com.foxminded.university.domain.WeeklyTimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeeklyTimeUnitService {

    private WeeklyTimeUnitDao weeklyTimeUnitDao;

    @Autowired
    public WeeklyTimeUnitService(WeeklyTimeUnitDao weeklyTimeUnitDao) {
        this.weeklyTimeUnitDao = weeklyTimeUnitDao;
    }

    public Optional<WeeklyTimeUnit> getById(Integer id) {
        return weeklyTimeUnitDao.getById(id);
    }

    public boolean delete(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.delete(weeklyTimeUnit);
    }

    public boolean update(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.update(weeklyTimeUnit);
    }

    public boolean create(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.create(weeklyTimeUnit);
    }

    public boolean isExist(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.isExist(weeklyTimeUnit);
    }
}
