package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.constraints.NotNull;

import java.util.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users") //Вывод списка юзеров
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping(value = "/users") //Добавляет юзера в список
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/users") //Обновляет информацию о юзере
    public User update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/{id}") // Возвращает пользователя по Id
    public User getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/friends") // Возвращает список пользователей, являющихся его друзьями.
    public List<User> getFriends(@PathVariable Integer id) {
        return userService.findAllUserFriends(id);
    }

    @PutMapping("/{id}/friends/{friendId}") // Добавление в друзья.
    public void addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}") // Удаление из друзей.
    public void deleteFriend(@PathVariable @NotNull Integer id, @PathVariable @NotNull Integer friendId) {
        userService.removeFriend(userService.getUserById(id), friendId);
    }

    @GetMapping("/{id}/friends/common/{otherId}") // Возвращает список друзей, общих с другим пользователем.
    public List<User> getCommonFriends(@PathVariable @NotNull Integer id, @PathVariable @NotNull Integer otherId) {
        return userService.getMutualFriends(id, otherId);
    }
}
