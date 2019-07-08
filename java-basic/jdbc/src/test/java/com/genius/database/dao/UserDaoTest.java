package com.genius.database.dao;

import com.genius.database.dao.user.UserDao;
import com.genius.database.dao.user.UserService;
import com.genius.database.dao.user.UserServiceImpl;
import com.genius.database.domain.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

@DisplayName("UserDao Propagation")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoTest {

    private static UserService userService;

    @BeforeAll
    public static void setUp() {
        UserDao userDao = new DaoFactory().userDao();
        userService = new UserServiceImpl(userDao);
        userService.truncate();
    }

    @Test
    @Order(1)
    @DisplayName("SAVE REQUIRED")
    public void save() {
        userService.save(User.builder().name("Genius").build());
    }

    @Test
    @Order(2)
    @DisplayName("REQUIRED")
    public void getUserTest() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> userService.get(0));
    }

    @Test
    @Order(3)
    @DisplayName("REQUIRED_NEW")
    public void getUserByNameTest() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> userService.getByName(""));
    }

    @Test
    @Order(4)
    @DisplayName("REQUIRED (REQUIRES_NEW NOT_SUPPORT) : Update")
    public void saveAndUpdateCase1() {
        Assertions.assertThrows(SQLException.class, () -> userService.saveAndUpdate(User.builder().name("Genius").build(), User.builder().id(1).name("Spring").build()));
    }
}
