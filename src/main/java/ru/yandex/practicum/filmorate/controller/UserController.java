package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import static ru.yandex.practicum.filmorate.validation.Validation.validationUser;

import java.util.*;

@RestController
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();
    private Integer generatedUserId = 1;

    @GetMapping("/users") //Вывод списка юзеров
    public Collection<User> findAllUsers() {
        return users.values();
    }

    @PostMapping(value = "/users") //Добавляет юзера в список
    public User addUser(@RequestBody User user) {
        validationUser(user);
        user.setId(generatedUserId++);
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping("/users") //Обновляет информацию о юзере
    public User update(@RequestBody User user) {
        validationUser(user);
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return user;
        } else {
            throw new NotFoundException("Не найдено.");
        }
    }
}
