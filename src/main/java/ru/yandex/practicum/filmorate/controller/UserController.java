package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.constraints.NotNull;

import java.util.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users") //Вывод списка юзеров
    public List<User> findAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/users") //Добавляет юзера в список
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/users") //Обновляет информацию о юзере
    public User update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/{id}") // Возвращает пользователя по Id
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}") // Удаляет пользователя по Id
    public User delete(@PathVariable @NotNull Integer id) {
        return userService.delete(id);
    }

    @GetMapping("/{id}/friends") // Возвращает список пользователей, являющихся его друзьями.
    public List<User> getFriends(@PathVariable Integer id) {
        return userService.getFriends(id);
    }

    @PutMapping("/{id}/friends/{friendId}") // Добавление в друзья.
    public void addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}") // Удаление из друзей.
    public void deleteFriend(@PathVariable @NotNull Integer id, @PathVariable @NotNull Integer friendId) {
        userService.deleteFriend(id, friendId);
    }

    @GetMapping("/{id}/friends/common/{otherId}") // Возвращает список друзей, общих с другим пользователем.
    public List<User> getCommonFriends(@PathVariable @NotNull Integer id, @PathVariable @NotNull Integer otherId) {
        return userService.getCommonFriends(id, otherId);
    }
}
