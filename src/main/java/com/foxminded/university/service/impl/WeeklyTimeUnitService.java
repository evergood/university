package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.WeeklyTimeUnit;
import com.foxminded.university.service.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeeklyTimeUnitService implements DaoService<WeeklyTimeUnit> {

    AbstractDao<WeeklyTimeUnit> weeklyTimeUnitDao;

    @Autowired
    public WeeklyTimeUnitService(AbstractDao<WeeklyTimeUnit> weeklyTimeUnitDao) {
        this.weeklyTimeUnitDao = weeklyTimeUnitDao;
    }

    @Override
    public Optional<WeeklyTimeUnit> getById(Integer id) {
        return weeklyTimeUnitDao.getById(id);
    }

    @Override
    public boolean delete(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.delete(weeklyTimeUnit);
    }

    @Override
    public boolean update(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.update(weeklyTimeUnit);
    }

    @Override
    public boolean create(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.create(weeklyTimeUnit);
    }

    @Override
    public boolean isExist(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.isExist(weeklyTimeUnit);
    }
}
