package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController userController;

    @BeforeEach
    public void setUp() {
        userController = new UserController();
    }

    @AfterEach
    public void tearDown() {
        userController = null;
    }

    @Test
    void findAllUsers() { // Тест вывода списка Users
        Collection<User> allUsersCreated = userController.findAllUsers();
        assertNotNull(allUsersCreated);
    }

    @Test
    void addUser() { //Тест добавления User
        User createdUser = userController.addUser(new User(1, "email@gmail.com", "Login", "Name", LocalDate.of(2000, 11, 11)));
        assertNotNull(createdUser.getId());
        assertNotNull(createdUser.getLogin());
        assertNotNull(createdUser.getName());
        assertNotNull(createdUser.getBirthday());
        assertNotNull(createdUser.getEmail());
    }

    @Test
    void update() { //Тест обновления User
        User createdUser = userController.addUser(new User(1, "email@gmail.com", "Login", "Name", LocalDate.of(2000, 11, 11)));
        User updatedUser = new User(1, "email@gmail.com", "UpdatedLogin", "Name", LocalDate.of(2000, 11, 11));
        userController.update(updatedUser);
        assertNotNull(createdUser.getId());
        assertNotNull(createdUser.getLogin());
        assertNotNull(createdUser.getName());
        assertNotNull(createdUser.getBirthday());
        assertNotNull(createdUser.getEmail());
    }
}
