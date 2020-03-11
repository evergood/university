package com.foxminded.university.service.major;

import com.foxminded.university.dao.UserDao;
import com.foxminded.university.domain.Role;
import com.foxminded.university.domain.User;
import config.ConfigTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    /*@Autowired
    private static PasswordEncoder passwordEncoder;*/

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Mock
    private UserDao userDao;
    @Mock
    private ValidatorImpl validator;
    @InjectMocks
    private UserService userService;

    @Test
    void userServiceShouldReturnUSerById() {
        Integer id = 10;
        User expected = User.builder().build();
        when(userDao.getById(id)).thenReturn(Optional.of(expected));

        User actual = userService.getById(id).get();

        assertThat(expected, is(actual));
    }

    @Test
    void userServiceShouldReturnUSerByEmail() {
        String email = "123@gmail.com";
        User expected = User.builder().email(email).build();
        when(userDao.getByEmail(email)).thenReturn(Optional.of(expected));

        User actual = userService.getByEmail(email).get();

        assertThat(expected, is(actual));
    }

    @Test
    void userServiceShouldDeleteUserById() {
        Integer id = 10;
        when(userDao.isExist(id)).thenReturn(false);

        boolean actual = userService.isExist(id);

        assertThat(actual, is(false));
    }

    @Test
    void userServiceShouldUpdateUserById() {
        Integer id = 10;
        User expected = User.builder().build();
        when(userDao.getById(id)).thenReturn(Optional.of(expected));

        userService.update(expected);
        User actual = userDao.getById(id).get();

        assertThat(expected, is(actual));
    }

    @Test
    void userServiceShouldCreateUserById() {
        Integer id = 10;
        User expected = User.builder().build();
        when(userDao.getById(id)).thenReturn(Optional.of(expected));

        userService.create(expected);
        User actual = userDao.getById(id).get();

        assertThat(expected, is(actual));
    }

    @Test
    void userServiceShouldSignIn() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String email = "123@gmail.com";
        String password = "124dgdg%##";
        User user = User.builder().email(email).password(passwordEncoder.encode(password)).build();
        when(userDao.getByEmail(email)).thenReturn(Optional.of(user));

        log.debug(userDao.getByEmail(email).toString());
        log.debug(String.valueOf(passwordEncoder.matches(password, user.getPassword())));
        log.debug(String.valueOf(userDao.getByEmail(email)
                .map(User::getPassword)
                .filter(pass -> passwordEncoder.matches(password, pass))
                .isPresent()));
        //log.debug(String.valueOf(userService.signIn(email, password)));
        boolean actual = userService.signIn(email, password);

        assertThat(actual, is(true));
    }

    @Test
    void userServiceShouldSignUp() {
        String email = "500@gmail.com";
        String password = "1464FGGG24@@dgdg";
        User expected = User.builder().email(email).password(password).build();
        when(userDao.getByEmail(email)).thenReturn(Optional.empty()).
                thenReturn(Optional.ofNullable(expected));

        User actual = userService.signUp(expected);

        assertThat(expected, is(actual));
    }

    @Test
    void userServiceShouldUpdateCredentials() {
        Integer id = 10;
        String email = "123@gmail.com";
        String password = "1464FGGG24@@dgdg";
        User currentUser = User.builder().role(Role.ADMIN).build();
        User expected = User.builder().email(email).password(passwordEncoder.encode(password)).build();
        when(userDao.getByEmail(email)).thenReturn(Optional.of(expected));
        when(userDao.update(expected)).thenReturn(true);

        boolean actual = userService.updateCredentials(currentUser, expected);

        assertThat(actual, is(true));
    }
}
