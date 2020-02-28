package com.foxminded.university.service.major;

import com.foxminded.university.dao.LecturerDao;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.domain.Role;
import com.foxminded.university.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LecturerServiceTest {

    @Mock
    private LecturerDao lecturerDao;
    @InjectMocks
    private LecturerService lecturerService;

    @Test
    void lecturerServiceShouldReturnLecturerById() {
        Integer id = 10;
        Lecturer expected = Lecturer.builder().build();
        when(lecturerDao.getById(id)).thenReturn(Optional.of(expected));

        Lecturer actual = lecturerService.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void lecturerServiceShouldDeleteLecturerById() {
        Integer id = 10;
        when(lecturerDao.isExist(id)).thenReturn(false);

        boolean actual = lecturerService.isExist(id);

        assertFalse(actual);
    }

    @Test
    void lecturerServiceShouldUpdateLecturer() {
        Integer id = 10;
        Lecturer expected = Lecturer.builder().build();
        when(lecturerDao.getById(id)).thenReturn(Optional.of(expected));

        lecturerService.update(expected);
        Lecturer actual = lecturerDao.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void lecturerServiceShouldInsertLecturer() {
        Integer id = 10;
        Lecturer expected = Lecturer.builder().build();
        when(lecturerDao.getById(id)).thenReturn(Optional.of(expected));

        lecturerService.create(expected);
        Lecturer actual = lecturerDao.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void lecturerServiceShouldPutMark() {
        User currentUser = User.builder().withRole(Role.LECTURER).build();
        Integer id = 10;
        Integer courseId = 1;
        Character mark = 'A';
        when(lecturerDao.putMark(id, courseId, mark)).thenReturn(true);

        boolean actual = lecturerService.putMark(currentUser, id, courseId, mark);

        assertTrue(actual);
    }
}
