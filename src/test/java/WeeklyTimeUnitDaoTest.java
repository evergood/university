import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.domain.WeeklyTimeUnit;
import config.ConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@JdbcTest
@ContextConfiguration(classes = ConfigTest.class)
public class WeeklyTimeUnitDaoTest {

    @Autowired
    @Qualifier("weeklyTimeUnitDao")
    CrudDao<WeeklyTimeUnit, Integer> weeklyTimeUnitDao;

    @Test
    void weeklyTimeUnitDaoShouldInsertWeeklyTimeUnit() {
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder()
                .withId(20)
                .withStartTime(LocalTime.of(10, 15))
                .withEndTime(LocalTime.of(12, 0))
                .withDayOfWeek(DayOfWeek.MONDAY)
                .build();
        weeklyTimeUnitDao.create(expected);
        WeeklyTimeUnit weeklyTimeUnit = weeklyTimeUnitDao.getById(20).get();
        assertEquals(expected, weeklyTimeUnit);
    }

    @Test
    void weeklyTimeUnitDaoShouldReturnWeeklyTimeUnitById() {
        WeeklyTimeUnit weeklyTimeUnit = weeklyTimeUnitDao.getById(10).get();
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder()
                .withStartTime(LocalTime.of(10, 0))
                .withEndTime(LocalTime.of(12, 0))
                .withDayOfWeek(DayOfWeek.THURSDAY)
                .withId(10)
                .build();
        assertEquals(expected, weeklyTimeUnit);
    }

    @Test
    void weeklyTimeUnitDaoShouldUpdateWeeklyTimeUnit() {
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder()
                .withId(15)
                .withStartTime(LocalTime.of(10, 15))
                .withEndTime(LocalTime.of(12, 0))
                .withDayOfWeek(DayOfWeek.MONDAY)
                .build();
        weeklyTimeUnitDao.update(expected);
        WeeklyTimeUnit weeklyTimeUnit = weeklyTimeUnitDao.getById(15).get();
        assertEquals(expected, weeklyTimeUnit);
    }

    @Test
    void weeklyTimeUnitDaoShouldDeleteWeeklyTimeUnit() {
        WeeklyTimeUnit weeklyTimeUnit = WeeklyTimeUnit.builder()
                .withId(10)
                .withStartTime(LocalTime.of(10, 15))
                .withEndTime(LocalTime.of(12, 0))
                .withDayOfWeek(DayOfWeek.MONDAY)
                .build();
        weeklyTimeUnitDao.delete(weeklyTimeUnit);
        boolean isExist = weeklyTimeUnitDao.isExist(weeklyTimeUnit);
        assertFalse(isExist);
    }
}
