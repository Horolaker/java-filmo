package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.Validation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class UserController {
    Validation validation = new Validation();
    private LinkedHashMap<Integer, User> users = new LinkedHashMap<>();
    private Integer generatedUserId = 1;

    @GetMapping("/users") //Вывод списка юзеров
    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    @PostMapping(value = "/users") //Добавляет юзера в список
    public User addUser(@RequestBody User user) {
        validation.validatedUser(user);
        user.setId(generatedUserId++);
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping("/users") //Обновляет информацию о юзере
    public User update(@RequestBody User user) {
        validation.validatedUser(user);
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return user;
        } else {
            throw new NotFoundException("Не найдено.");
        }
    }
}
