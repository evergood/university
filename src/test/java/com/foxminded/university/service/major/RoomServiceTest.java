package com.foxminded.university.service.major;

import com.foxminded.university.dao.RoomDao;
import com.foxminded.university.domain.Room;
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
class RoomServiceTest {

    @Mock
    private RoomDao roomDao;
    @InjectMocks
    private RoomService roomService;

    @Test
    void roomServiceShouldReturnRoomById() {
        Integer id = 10;
        Room expected = new Room(id);
        when(roomDao.getById(id)).thenReturn(Optional.of(expected));

        Room actual = roomService.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void roomServiceShouldDeleteRoomById() {
        Integer id = 10;
        when(roomDao.isExist(id)).thenReturn(false);

        boolean actual = roomService.isExist(id);

        assertFalse(actual);
    }

    @Test
    void roomServiceShouldUpdateRoom() {
        Integer id = 10;
        Room expected = new Room(id);
        when(roomDao.getById(id)).thenReturn(Optional.of(expected));

        roomService.update(expected);
        Room actual = roomService.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void roomServiceShouldInsertRoom() {
        Integer id = 10;
        Room expected = new Room(id);
        when(roomDao.getById(id)).thenReturn(Optional.of(expected));

        roomService.create(expected);
        Room actual = roomService.getById(id).get();

        assertEquals(expected, actual);
    }
}
