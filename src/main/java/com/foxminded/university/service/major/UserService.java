package com.foxminded.university.service.major;

import com.foxminded.university.dao.UserDao;
import com.foxminded.university.domain.Role;
import com.foxminded.university.domain.User;
import com.foxminded.university.service.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("user_service")
public class UserService extends PageService<User> {

    private final UserDao userDao;
    private final Validator<User> validator;
    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerService.class);

    @Autowired
    public UserService(UserDao userDao, ValidatorImpl validator) {
        super(userDao);
        this.userDao = userDao;
        this.validator = validator;
    }

    public Optional<User> getById(Integer userId) {
        return userDao.getById(userId);
    }

    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public boolean deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    public boolean update(User user) {
        return userDao.update(user);
    }

    public boolean create(User user) {
        return userDao.create(user);
    }

    public boolean isExist(Integer id) {
        return userDao.isExist(id);
    }

    public boolean signIn(String email, String password) {
        if (email == null || password == null) {
            LOGGER.error("Email or password is null");
            throw new RuntimeException("Email or password is null");
        }
        return userDao.getByEmail(email)
                .map(User::getPassword)
                .filter(pass -> pass.equals(password))
                .isPresent();
    }

    public User signUp(User user) {
        LOGGER.debug("Starting signup");
        validator.validate(user);
        if (userDao.getByEmail(user.getEmail()).isPresent()) {
            LOGGER.error("User already exists");
            throw new RuntimeException("User already exists");
        }
        userDao.create(user);
        return userDao.getByEmail(user.getEmail()).get();
    }

    public boolean updateCredentials(User currentUser, User targetUser) {
        if (currentUser.getRole() != Role.ADMIN) {
            LOGGER.error("Current user doesn't have rights for this action");
            throw new RuntimeException("You don't have rights for this action");
        }
        userDao.getByEmail(targetUser.getEmail())
                .map(User::getEmail)
                .filter(email -> email.equals(targetUser.getEmail()))
                .orElseThrow(()-> new RuntimeException("E-mail already exists"));
        return userDao.update(targetUser);
    }
}
