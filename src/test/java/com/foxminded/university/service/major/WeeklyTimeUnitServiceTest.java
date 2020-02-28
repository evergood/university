package com.foxminded.university.service.major;

import com.foxminded.university.dao.WeeklyTimeUnitDao;
import com.foxminded.university.domain.WeeklyTimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeeklyTimeUnitServiceTest {

    @Mock
    private WeeklyTimeUnitDao weeklyTimeUnitDao;
    @InjectMocks
    private WeeklyTimeUnitService weeklyTimeUnitService;

    @Test
    void studentServiceShouldReturnTimeUnitById() {
        Integer id = 10;
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder().withId(1).build();
        when(weeklyTimeUnitDao.getById(id)).thenReturn(Optional.of(expected));

        WeeklyTimeUnit actual = weeklyTimeUnitService.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void studentServiceShouldDeleteTimeUnitById() {
        Integer id = 10;
        when(weeklyTimeUnitDao.isExist(id)).thenReturn(false);

        boolean actual = weeklyTimeUnitService.isExist(id);

        assertFalse(actual);
    }

    @Test
    void studentServiceShouldUpdateTimeUnit() {
        Integer id = 10;
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder().withId(1).build();
        when(weeklyTimeUnitDao.getById(id)).thenReturn(Optional.of(expected));

        weeklyTimeUnitService.update(expected);
        WeeklyTimeUnit actual = weeklyTimeUnitDao.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void studentServiceShouldInsertTimeUnit() {
        Integer id = 10;
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder().withId(1).build();
        when(weeklyTimeUnitDao.getById(id)).thenReturn(Optional.of(expected));

        weeklyTimeUnitService.create(expected);
        WeeklyTimeUnit actual = weeklyTimeUnitDao.getById(id).get();

        assertEquals(expected, actual);
    }
}
