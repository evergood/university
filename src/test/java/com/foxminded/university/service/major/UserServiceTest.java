package com.foxminded.university.service.major;

import com.foxminded.university.dao.UserDao;
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
class UserServiceTest {

    @Mock
    private UserDao userDao;
    @Mock
    private ValidatorImpl validator;
    @InjectMocks
    private UserService userService;

    @Test
    void userServiceShouldReturnUSerById() {
        Integer id = 10;
        User expected = User.builder().withId(1).build();
        when(userDao.getById(id)).thenReturn(Optional.of(expected));

        User actual = userService.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void userServiceShouldReturnUSerByEmail() {
        String email = "123@gmail.com";
        User expected = User.builder().withId(1).withEmail(email).build();
        when(userDao.getByEmail(email)).thenReturn(Optional.of(expected));

        User actual = userService.getByEmail(email).get();

        assertEquals(expected, actual);
    }

    @Test
    void userServiceShouldDeleteUserById() {
        Integer id = 10;
        when(userDao.isExist(id)).thenReturn(false);

        boolean actual = userService.isExist(id);

        assertFalse(actual);
    }

    @Test
    void userServiceShouldUpdateUserById() {
        Integer id = 10;
        User expected = User.builder().withId(1).build();
        when(userDao.getById(id)).thenReturn(Optional.of(expected));

        userService.update(expected);
        User actual = userDao.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void userServiceShouldCreateUserById() {
        Integer id = 10;
        User expected = User.builder().withId(1).build();
        when(userDao.getById(id)).thenReturn(Optional.of(expected));

        userService.create(expected);
        User actual = userDao.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void userServiceShouldSignIn() {
        String email = "123@gmail.com";
        String password = "124dgdg%##";
        User user = User.builder().withEmail(email).withPassword(password).build();
        when(userDao.getByEmail(email)).thenReturn(Optional.ofNullable(user));

        boolean actual = userService.signIn(email, password);

        assertTrue(actual);
    }

    @Test
    void userServiceShouldSignUp() {
        Integer id = 10;
        String email = "123@gmail.com";
        String password = "1464FGGG24@@dgdg";
        User expected = User.builder().withId(id).withEmail(email).withPassword(password).build();
        when(userDao.getById(id)).thenReturn(Optional.empty());
        when(userDao.getByEmail(email)).thenReturn(Optional.ofNullable(expected));

        User actual = userService.signUp(expected);

        assertEquals(expected, actual);
    }

    @Test
    void userServiceShouldUpdateCredentials() {
        Integer id = 10;
        String email = "123@gmail.com";
        String password = "1464FGGG24@@dgdg";
        User currentUser = User.builder().withRole(Role.ADMIN).build();
        User expected = User.builder().withId(id).withEmail(email).withPassword(password).build();
        when(userDao.getByEmail(email)).thenReturn(Optional.of(expected));
        when(userDao.update(expected)).thenReturn(true);

        boolean actual = userService.updateCredentials(currentUser, expected);

        assertTrue(actual);
    }
}
