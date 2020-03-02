package com.foxminded.university.service.major;

import com.foxminded.university.dao.WeeklyTimeUnitDao;
import com.foxminded.university.domain.WeeklyTimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeeklyTimeUnitService {

    private final WeeklyTimeUnitDao weeklyTimeUnitDao;

    public Optional<WeeklyTimeUnit> getById(Integer id) {
        return weeklyTimeUnitDao.getById(id);
    }

    public boolean deleteById(Integer id) {
        return weeklyTimeUnitDao.deleteById(id);
    }

    public boolean update(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.update(weeklyTimeUnit);
    }

    public boolean create(WeeklyTimeUnit weeklyTimeUnit) {
        return weeklyTimeUnitDao.create(weeklyTimeUnit);
    }

    public boolean isExist(Integer id) {
        return weeklyTimeUnitDao.isExist(id);
    }
}
